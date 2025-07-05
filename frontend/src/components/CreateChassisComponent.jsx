import React, { Component } from 'react'
import ChassisService from '../services/ChassisService';

class CreateChassisComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
                name: '',
                serialNum: '',
                type: ''
        }
        this.changenameHandler = this.changenameHandler.bind(this);
        this.changeserialNumHandler = this.changeserialNumHandler.bind(this);
        this.changeTypeHandler = this.changeTypeHandler.bind(this);
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            ChassisService.getChassisById(this.state.id).then( (res) =>{
                let chassis = res.data;
                this.setState({
                    name: chassis.name,
                    serialNum: chassis.serialNum,
                    type: chassis.type
                });
            });
        }        
    }
    saveOrUpdateChassis = (e) => {
        e.preventDefault();
        let chassis = {
                chassisId: this.state.id,
                name: this.state.name,
                serialNum: this.state.serialNum,
                type: this.state.type
            };
        console.log('chassis => ' + JSON.stringify(chassis));

        // step 5
        if(this.state.id === '_add'){
            chassis.chassisId=''
            ChassisService.createChassis(chassis).then(res =>{
                this.props.history.push('/chassiss');
            });
        }else{
            ChassisService.updateChassis(chassis).then( res => {
                this.props.history.push('/chassiss');
            });
        }
    }
    
    changenameHandler= (event) => {
        this.setState({name: event.target.value});
    }
    changeserialNumHandler= (event) => {
        this.setState({serialNum: event.target.value});
    }
    changeTypeHandler= (event) => {
        this.setState({type: event.target.value});
    }

    cancel(){
        this.props.history.push('/chassiss');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add Chassis</h3>
        }else{
            return <h3 className="text-center">Update Chassis</h3>
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
                                            <label> name:&emsp; </label>
                                                <input placeholder="name" name="name" className="form-control" value={this.state.name} onChange={this.changenameHandler}/>

                                            <label> serialNum:&emsp; </label>
                                                <input placeholder="serialNum" name="serialNum" className="form-control" value={this.state.serialNum} onChange={this.changeserialNumHandler}/>

                                            <label> Type:&emsp; </label>
                                                <select value={this.state.type} onChange={this.changeTypeHandler}>
                      <option name="Type" className="form-control" >
                          ShortPassenger
                      </option>
                      <option name="Type" className="form-control" >
                          LongPassenger
                      </option>
                      <option name="Type" className="form-control" >
                          SUV
                      </option>
                      <option name="Type" className="form-control" >
                          Truck
                      </option>
                    </select>

                                        </div>

                                        <button className="btn btn-outline-success" onClick={this.saveOrUpdateChassis}>Save</button>
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

export default CreateChassisComponent
