import React, { Component } from 'react'
import ChassisService from '../services/ChassisService';

class UpdateChassisComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
                name: '',
                serialNum: '',
                type: ''
        }
        this.updateChassis = this.updateChassis.bind(this);

        this.changenameHandler = this.changenameHandler.bind(this);
        this.changeserialNumHandler = this.changeserialNumHandler.bind(this);
        this.changeTypeHandler = this.changeTypeHandler.bind(this);
    }

    componentDidMount(){
        ChassisService.getChassisById(this.state.id).then( (res) =>{
            let chassis = res.data;
            this.setState({
                name: chassis.name,
                serialNum: chassis.serialNum,
                type: chassis.type
            });
        });
    }

    updateChassis = (e) => {
        e.preventDefault();
        let chassis = {
            chassisId: this.state.id,
            name: this.state.name,
            serialNum: this.state.serialNum,
            type: this.state.type
        };
        console.log('chassis => ' + JSON.stringify(chassis));
        console.log('id => ' + JSON.stringify(this.state.id));
        ChassisService.updateChassis(chassis).then( res => {
            this.props.history.push('/chassiss');
        });
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

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update Chassis</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> name: </label>
                                                <input placeholder="name" name="name" className="form-control" value={this.state.name} onChange={this.changenameHandler}/>

                                            <label> serialNum: </label>
                                                <input placeholder="serialNum" name="serialNum" className="form-control" value={this.state.serialNum} onChange={this.changeserialNumHandler}/>

                                            <label> Type: </label>
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
                                        <button className="btn btn-success" onClick={this.updateChassis}>Save</button>
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

export default UpdateChassisComponent
