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
    this.updateCharts = this.updateCharts.bind(this);
  }

  componentDidMount() {
    this.fetch();
  }

  updateCharts() {
    // console.log(this.state.data);
    let dailyWeight = []
    let dailyEnergy = []
    let dailyProtein = []
    let dailyCarb = []
    let dailyFat = []
    this.state.data.daily.forEach((dailyData) => {
      let date = moment(dailyData.date, 'DD-MM-YYYY').toDate()
      dailyWeight.push({x: date, y: dailyData.weight})
      dailyEnergy.push({x: date, y: dailyData.energy})
      dailyProtein.push({x: date, y: dailyData.protein.weight})
      dailyCarb.push({x: date, y: dailyData.carb.weight})
      dailyFat.push({x: date, y: dailyData.fat.weight})
    })
    Highcharts.chart('dailyMacroChart', {
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

  }

  fetch(){
    Api.consumptionHistory().then(
      (data) => {
        data.daily = data.daily.sort((a, b) => {
          return moment(a.date, 'DD-MM-YYYY').unix() - moment(b.date, 'DD-MM-YYYY').unix()
        })
        this.setState({data: data})
        this.updateCharts()
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
