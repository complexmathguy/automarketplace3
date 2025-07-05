import React, { Component } from 'react'
import BrakesService from '../services/BrakesService';

class UpdateBrakesComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
                serialNum: '',
                name: '',
                type: ''
        }
        this.updateBrakes = this.updateBrakes.bind(this);

        this.changeserialNumHandler = this.changeserialNumHandler.bind(this);
        this.changenameHandler = this.changenameHandler.bind(this);
        this.changeTypeHandler = this.changeTypeHandler.bind(this);
    }

    componentDidMount(){
        BrakesService.getBrakesById(this.state.id).then( (res) =>{
            let brakes = res.data;
            this.setState({
                serialNum: brakes.serialNum,
                name: brakes.name,
                type: brakes.type
            });
        });
    }

    updateBrakes = (e) => {
        e.preventDefault();
        let brakes = {
            brakesId: this.state.id,
            serialNum: this.state.serialNum,
            name: this.state.name,
            type: this.state.type
        };
        console.log('brakes => ' + JSON.stringify(brakes));
        console.log('id => ' + JSON.stringify(this.state.id));
        BrakesService.updateBrakes(brakes).then( res => {
            this.props.history.push('/brakess');
        });
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

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update Brakes</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> serialNum: </label>
                                                <input placeholder="serialNum" name="serialNum" className="form-control" value={this.state.serialNum} onChange={this.changeserialNumHandler}/>

                                            <label> name: </label>
                                                <input placeholder="name" name="name" className="form-control" value={this.state.name} onChange={this.changenameHandler}/>

                                            <label> Type: </label>
                                                <select value={this.state.type} onChange={this.changeTypeHandler}>
                      <option name="Type" className="form-control" >
                          Standard
                      </option>
                      <option name="Type" className="form-control" >
                          ABS
                      </option>
                    </select>

                                        </div>
                                        <button className="btn btn-success" onClick={this.updateBrakes}>Save</button>
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

export default UpdateBrakesComponent
