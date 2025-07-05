import React, { Component } from 'react'
import TransmissionService from '../services/TransmissionService';

class CreateTransmissionComponent extends Component {
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
            TransmissionService.getTransmissionById(this.state.id).then( (res) =>{
                let transmission = res.data;
                this.setState({
                    name: transmission.name,
                    serialNum: transmission.serialNum,
                    type: transmission.type
                });
            });
        }        
    }
    saveOrUpdateTransmission = (e) => {
        e.preventDefault();
        let transmission = {
                transmissionId: this.state.id,
                name: this.state.name,
                serialNum: this.state.serialNum,
                type: this.state.type
            };
        console.log('transmission => ' + JSON.stringify(transmission));

        // step 5
        if(this.state.id === '_add'){
            transmission.transmissionId=''
            TransmissionService.createTransmission(transmission).then(res =>{
                this.props.history.push('/transmissions');
            });
        }else{
            TransmissionService.updateTransmission(transmission).then( res => {
                this.props.history.push('/transmissions');
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
        this.props.history.push('/transmissions');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add Transmission</h3>
        }else{
            return <h3 className="text-center">Update Transmission</h3>
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
                          Automatic
                      </option>
                      <option name="Type" className="form-control" >
                          Manual
                      </option>
                    </select>

                                        </div>

                                        <button className="btn btn-outline-success" onClick={this.saveOrUpdateTransmission}>Save</button>
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

export default CreateTransmissionComponent
