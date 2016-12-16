import DatePicker from 'service/DatePicker.js'
import FoodRow from 'food/FoodRow.js'

export default class ConsumptionHistory extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      data: {
        total: {},
        daily: []
      }
    }

    this.input = {
      fromDate: moment().subtract(1, 'month').format('DD-MM-YYYY'),
      toDate: moment().format('DD-MM-YYYY')
    }

    this.fetch = this.fetch.bind(this);
    this.updateDailyCharts = this.updateDailyCharts.bind(this);
  }

  componentDidMount() {
    this.fetch();
  }

  updateDailyCharts() {
    // console.log(this.state.data);
    let dailyWeight = []
    let dailyEnergy = []
    let dailyProtein = []
    let dailyCarb = []
    let dailyFat = []
    let dailyMicro = {
      saturatedFat: [],
      sugar: [],
      fibre: [],
      cholesterol: [],
      calcium: [],
      iron: [],
      sodium: [],
      potassium: [],
      magnesium: [],
      phosphorus: [],
      thiamin: [],
      riboflavin: [],
      niacin: [],
      folate: []
    }
    this.state.data.daily.forEach((dailyData) => {
      let date = moment(dailyData.date, 'DD-MM-YYYY').toDate()
      dailyWeight.push({x: date, y: dailyData.weight})
      dailyEnergy.push({x: date, y: dailyData.energy})
      dailyProtein.push({x: date, y: dailyData.protein.weight})
      dailyCarb.push({x: date, y: dailyData.carb.weight})
      dailyFat.push({x: date, y: dailyData.fat.weight})
      Object.keys(dailyMicro).forEach((key) => {
        dailyMicro[key].push({x: date, y: dailyData.others[key]})
      })
    })
    Highcharts.chart('dailyMacroChart', {
      chart: {
        height: 300,
        spacingTop: 40
      },
      title: {
        text: 'Macronutrients'
      },
      tooltip: {
        shared: true,
        valueDecimals: 2,
        valueSuffix: ' g'
      },
      xAxis: {
        type: 'datetime'
      },
      series: [
        {type: 'line', name: 'Protein', data: dailyProtein},
        {type: 'line', name: 'Carbonhydrate', data: dailyCarb},
        {type: 'line', name: 'Fat', data: dailyFat},
        {type: 'line', name: 'Total weight', data: dailyWeight},
      ]
    })
    Highcharts.chart('dailyEnergyChart', {
      chart: {
        height: 300,
        spacingTop: 40
      },
      title: {
        text: 'Energy'
      },
      legend: {
        enabled: false
      },
      tooltip: {
        shared: true,
        valueDecimals: 0,
        valueSuffix: ' kcal'
      },
      xAxis: {
        type: 'datetime'
      },
      series: [
        {type: 'line', name: 'Energy', data: dailyEnergy},
      ]
    })
    Highcharts.chart('dailyMicroChart', {
      chart: {
        height: 300,
        spacingTop: 40
      },
      title: {
        text: 'Micronutrients'
      },
      legend: {
        enabled: false
      },
      tooltip: {
        shared: true,
        valueDecimals: 2
      },
      xAxis: {
        type: 'datetime'
      },
      series: [
        {type: 'line', tooltip: {valueSuffix: ' g'}, name: 'Saturated fat', data: dailyMicro.saturatedFat},
        {type: 'line', tooltip: {valueSuffix: ' g'}, name: 'Sugar', data: dailyMicro.sugar},
        {type: 'line', tooltip: {valueSuffix: ' g'}, name: 'Fibre', data: dailyMicro.fibre},
        {type: 'line', tooltip: {valueSuffix: ' mg'}, name: 'Cholesterol', data: dailyMicro.cholesterol},
        {type: 'line', tooltip: {valueSuffix: ' mg'}, name: 'Calcium', data: dailyMicro.calcium},
        {type: 'line', tooltip: {valueSuffix: ' mg'}, name: 'Iron', data: dailyMicro.iron},
        {type: 'line', tooltip: {valueSuffix: ' mg'}, name: 'Sodium', data: dailyMicro.sodium},
        {type: 'line', tooltip: {valueSuffix: ' mg'}, name: 'Potassium', data: dailyMicro.potassium},
        {type: 'line', tooltip: {valueSuffix: ' mg'}, name: 'Magnesium', data: dailyMicro.magnesium},
        {type: 'line', tooltip: {valueSuffix: ' mg'}, name: 'Phosphorus', data: dailyMicro.phosphorus},
        {type: 'line', tooltip: {valueSuffix: ' mg'}, name: 'Thiamin', data: dailyMicro.thiamin},
        {type: 'line', tooltip: {valueSuffix: ' mg'}, name: 'Riboflavin', data: dailyMicro.riboflavin},
        {type: 'line', tooltip: {valueSuffix: ' NE'}, name: 'Niacin', data: dailyMicro.niacin},
        {type: 'line', tooltip: {valueSuffix: ' DFE'}, name: 'Folate', data: dailyMicro.folate},
      ]
    })

  }

  fetch(){
    Api.consumptionHistory().then(
      (data) => {
        data.daily = data.daily.sort((a, b) => {
          return moment(a.date, 'DD-MM-YYYY').unix() - moment(b.date, 'DD-MM-YYYY').unix()
        })
        this.setState({data: data})
        this.updateDailyCharts()
      }
    ).catch(
      (error) => {
        console.log(error);
      }
    )
  }

  fromDateChanged(e) {
    this.input.fromDate = moment(e).format('DD-MM-YYYY');
  }

  toDateChanged(e) {
    this.input.toDate = moment(e).format('DD-MM-YYYY');
  }

  render() {
    return (
      <div className="ui segment">
        <div style={{display:'flex', alignItems: 'center'}}>
          <span style={{marginLeft:10, marginRight:10}}>From</span>
          <DatePicker name="consumptionStartDate" placeholder="Start Date" default={this.input.fromDate} onChange={this.fromDateChanged.bind(this)}/>
          <span style={{marginLeft:10, marginRight:10}}>to</span>
          <DatePicker name="consumptionEndDate" placeholder="End Date" default={this.input.toDate} onChange={this.toDateChanged.bind(this)}/>
          <button className="ui button" style={{marginLeft:10, marginRight:10}}>Refresh</button>
        </div>
        <div>
          <div id="dailyMacroChart"></div>
          <div id="dailyEnergyChart"></div>
          <div id="dailyMicroChart"></div>
        </div>
        <div style={{marginTop:20}}>
          {this.state.data.daily.map((dailyData) => {
            return (
              <div key={dailyData.date}>
                <h3>{dailyData.date}</h3>
                {dailyData.ateFoods.map((ateFood) => {
                  return <FoodRow key={ateFood.created} data={ateFood.food}/>
                })}
              </div>
            )
          })}
        </div>
      </div>
    )
  }
}
