import MultipleSelect from 'service/MultipleSelect.js'

export default class MyDiet extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};

    this.fetch = this.fetch.bind(this);
    this.dietsSelected = this.dietsSelected.bind(this);
    this.addDiets = this.addDiets.bind(this);
  }

  fetch(){
    Api.getMyDiets().then(
      (data) => {
        console.log(data);
      }
    ).catch(
      (error) => {
        console.log(error);
      }
    )
  }
  componentWillMount() {
    this.fetch();
  }

  setDietSelectionOptions(){
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

  openAddDietModal() {
      $('#addDietModal').modal('show')
  }

  openSuccessModal() {
    $('#addDietSuccess').modal('show')
  }

  dietsSelected(diets) {
    this.setState({diets: diets})
    console.log(diets);
  }

  addDiets(event){
    let promises = [];
    this.state.diets.forEach(function(diet, index){
      promises.push(Api.addDiet(diet));
    })
    console.log(promises);
    Promise.all(promises).then(
      () => {
        console.log('yay o/');
        this.openSuccessModal();
      }
    ).catch(
      (error) => {
        console.log(error);
        this.setState({errors: error.data});
      }
    )
  }
  render() {
    return (
      <div className="ui segment">
        <div>

          <div>
            <button className="ui button" type="button" onClick={this.openAddDietModal}>
              Add diet
            </button>
            <div id='addDietSuccess' className="ui small modal">
              <div className="ui message success">
                <i className="close icon"></i>
                <div className="header">
                  Success!
                </div>
                <p>The diet has been added to your diets section</p>
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
                    <MultipleSelect onChange={this.dietsSelected} setOptions={this.setDietSelectionOptions} name="diets" placeholder="Select diets"/>
                  </div>
                  <button className="ui button" type="button" style={{width:'100%'}} onClick={this.addDiets}>
                    Submit
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
