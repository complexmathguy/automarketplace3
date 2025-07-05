import React, { Component } from 'react'
import BrakesService from '../services/BrakesService';

class CreateBrakesComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
                serialNum: '',
                name: '',
                type: ''
        }
        this.changeserialNumHandler = this.changeserialNumHandler.bind(this);
        this.changenameHandler = this.changenameHandler.bind(this);
        this.changeTypeHandler = this.changeTypeHandler.bind(this);
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            BrakesService.getBrakesById(this.state.id).then( (res) =>{
                let brakes = res.data;
                this.setState({
                    serialNum: brakes.serialNum,
                    name: brakes.name,
                    type: brakes.type
                });
            });
        }        
    }
    saveOrUpdateBrakes = (e) => {
        e.preventDefault();
        let brakes = {
                brakesId: this.state.id,
                serialNum: this.state.serialNum,
                name: this.state.name,
                type: this.state.type
            };
        console.log('brakes => ' + JSON.stringify(brakes));

        // step 5
        if(this.state.id === '_add'){
            brakes.brakesId=''
            BrakesService.createBrakes(brakes).then(res =>{
                this.props.history.push('/brakess');
            });
        }else{
            BrakesService.updateBrakes(brakes).then( res => {
                this.props.history.push('/brakess');
            });
        }
    }
    
    changeserialNumHandler= (event) => {
        this.setState({serialNum: event.target.value});
    }
    changenameHandler= (event) => {
        this.setState({name: event.target.value});
    }
    changeTypeHandler= (event) => {
        this.setState({type: event.target.value});
    }

    cancel(){
        this.props.history.push('/brakess');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add Brakes</h3>
        }else{
            return <h3 className="text-center">Update Brakes</h3>
        }
    }
    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                {
                                    this.getTitle()
                                }
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> serialNum:&emsp; </label>
                                                <input placeholder="serialNum" name="serialNum" className="form-control" value={this.state.serialNum} onChange={this.changeserialNumHandler}/>

                                            <label> name:&emsp; </label>
                                                <input placeholder="name" name="name" className="form-control" value={this.state.name} onChange={this.changenameHandler}/>

                                            <label> Type:&emsp; </label>
                                                <select value={this.state.type} onChange={this.changeTypeHandler}>
                      <option name="Type" className="form-control" >
                          Standard
                      </option>
                      <option name="Type" className="form-control" >
                          ABS
                      </option>
                    </select>

                                        </div>

                                        <button className="btn btn-outline-success" onClick={this.saveOrUpdateBrakes}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                   </div>
            </div>
        )
    }
}

export default CreateBrakesComponent
