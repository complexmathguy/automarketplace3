import React, { Component } from 'react'
import TransmissionService from '../services/TransmissionService';

class UpdateTransmissionComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
                name: '',
                serialNum: '',
                type: ''
        }
        this.updateTransmission = this.updateTransmission.bind(this);

        this.changenameHandler = this.changenameHandler.bind(this);
        this.changeserialNumHandler = this.changeserialNumHandler.bind(this);
        this.changeTypeHandler = this.changeTypeHandler.bind(this);
    }

    componentDidMount(){
        TransmissionService.getTransmissionById(this.state.id).then( (res) =>{
            let transmission = res.data;
            this.setState({
                name: transmission.name,
                serialNum: transmission.serialNum,
                type: transmission.type
            });
        });
    }

    updateTransmission = (e) => {
        e.preventDefault();
        let transmission = {
            transmissionId: this.state.id,
            name: this.state.name,
            serialNum: this.state.serialNum,
            type: this.state.type
        };
        console.log('transmission => ' + JSON.stringify(transmission));
        console.log('id => ' + JSON.stringify(this.state.id));
        TransmissionService.updateTransmission(transmission).then( res => {
            this.props.history.push('/transmissions');
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
        this.props.history.push('/transmissions');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update Transmission</h3>
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
                          Automatic
                      </option>
                      <option name="Type" className="form-control" >
                          Manual
                      </option>
                    </select>

                                        </div>
                                        <button className="btn btn-success" onClick={this.updateTransmission}>Save</button>
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

export default UpdateTransmissionComponent
