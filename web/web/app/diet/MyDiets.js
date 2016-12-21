import MultipleSelect from 'service/MultipleSelect.js'
import DietRow from 'diet/DietRow.js'

export default class MyDiet extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      myDiets: [],
      addDiets: [],
      removeDiets: []
    };

    this.fetchMyDiets = this.fetchMyDiets.bind(this);
    this.addDietsSelected = this.addDietsSelected.bind(this);
    this.removeDietsSelected = this.removeDietsSelected.bind(this);
    this.openAddDietModal = this.openAddDietModal.bind(this);
    this.openRemoveDietModal = this.openRemoveDietModal.bind(this);
    this.addDiets = this.addDiets.bind(this);
    this.removeDiets = this.removeDiets.bind(this);
    this.navigateToCreateDietPage = this.navigateToCreateDietPage.bind(this);
  }

  fetchMyDiets(){
    Api.getMyDiets().then(data => {
        this.setState({myDiets: data})
      }
    ).catch(error => {
      this.setState({errors: error})
      }
    )
  }

  componentWillMount() {
    this.fetchMyDiets();
  }

  addDietOptions(){
    const self = this
    Api.getDiets().then(
      (data) => {
        self.setState({options: data})
      }
    ).catch(
      (error) => {
        self.setState({errors: error})
      }
    )
  }

  removeDietOptions(){
    const self = this;
    Api.getMyDiets().then(
      (data) => {
        self.setState({options: data})
      }
    ).catch(
      (error) => {
        self.setState({errors: error.data})
      }
    )
  }

  openAddDietModal() {
      $('#addDietModal').modal('show')
  }
  openRemoveDietModal() {
      $('#removeDietModal').modal('show')
  }

  addDietsSelected(addDiets) {
    this.setState({addDiets: addDiets})
  }

  removeDietsSelected(removeDiets){
    this.setState({removeDiets: removeDiets})
  }

  addDiets(event){
    let dietList = this.state.addDiets.map(function(diet,index){
      return parseInt(diet);
    });
    let promises = [];
    dietList.forEach(function(diet, index){
      promises.push(Api.addDiet(diet));
    });
    Promise.all(promises).then(data => {
        this.fetchMyDiets();
        $('#addDietSuccess').modal('show')
      }
    ).catch(error => {
        this.setState({errors: error.data});
        this.fetchMyDiets();
      }
    );
  }

  removeDiets(event){
    let dietList = this.state.removeDiets.map(function(diet,index){
      return parseInt(diet)
    });
    let promises = [];
    dietList.forEach(function(diet, index){
      promises.push(Api.removeDiet(diet));
    });
    Promise.all(promises).then(data => {
        this.fetchMyDiets();
        $('#removeDietSuccess').modal('show')
      }
    ).catch(error => {
        this.setState({errors: error.data});
        this.fetchMyDiets();
      }
    );
  }

  navigateToCreateDietPage(){
    router.navigate('../createDiet');
  }
  render() {
    return (
      <div className="ui segments">
        {/* Buttons */}
        <div className="ui segment">
          <button className="ui button" type="button" onClick={this.navigateToCreateDietPage}>
            Create diet
          </button>
          <button className="ui button" type="button" onClick={this.openAddDietModal}>
            Add diet
          </button>
          <button className="ui button" type="button" onClick={this.openRemoveDietModal}>
            Remove diet
          </button>
        </div>

        {/* Modals */}
        {/* Add Diet Modal */}
        <div id='addDietSuccess' className="ui small modal">
          <div className="ui message success">
            <i className="close icon"></i>
            <div className="header">
              Success!
            </div>
            <p>Diets has been added to your diets section</p>
          </div>
        </div>
        <div id='addDietModal' className="ui small modal">
          <i className="close icon"></i>
          <div className="header">
            Select diets to add
          </div>
          <div className="content">
            <form className="ui form">
              <div className="field">
                <MultipleSelect onChange={this.addDietsSelected} setOptions={this.addDietOptions} name="addDiets" placeholder="Select diets"/>
              </div>
              <button className="ui button" type="button" style={{width:'100%'}} onClick={this.addDiets}>
                Submit
              </button>
            </form>
          </div>
        </div>

        {/* Remove Diet Modal */}
        <div id='removeDietSuccess' className="ui small modal">
          <div className="ui message success">
            <i className="close icon"></i>
            <div className="header">
              Success!
            </div>
            <p>Diets has been removed from your diets section</p>
          </div>
        </div>
        <div id='removeDietModal' className="ui small modal">
          <i className="close icon"></i>
          <div className="header">
            Select diets to remove
          </div>
          <div className="content">
            <form className="ui form">
              <div className="field">
                <MultipleSelect onChange={this.removeDietsSelected} setOptions={this.removeDietOptions} name="removeDiets" placeholder="Select diets"/>
              </div>
              <button className="ui button" type="button" style={{width:'100%'}} onClick={this.removeDiets}>
                Submit
              </button>
            </form>
          </div>
        </div>

        {/* Diet List */}
        <div className="ui segment">
          <h2 className="ui header" style={{textAlign:'center'}}>My Diets</h2>
        </div>
        <div className="ui segment">
          <div className="ui relaxed list">
            {this.state.myDiets.map(function(diet, index){
              return (
                <DietRow key={index} data={diet}/>
              )
            })}
          </div>
        </div>
      </div>
    );
  }
}
