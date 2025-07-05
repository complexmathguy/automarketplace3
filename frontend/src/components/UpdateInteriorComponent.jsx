import React, { Component } from 'react'
import InteriorService from '../services/InteriorService';

class UpdateInteriorComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
                serialNum: '',
                name: ''
        }
        this.updateInterior = this.updateInterior.bind(this);

        this.changeserialNumHandler = this.changeserialNumHandler.bind(this);
        this.changenameHandler = this.changenameHandler.bind(this);
    }

    componentDidMount(){
        InteriorService.getInteriorById(this.state.id).then( (res) =>{
            let interior = res.data;
            this.setState({
                serialNum: interior.serialNum,
                name: interior.name
            });
        });
    }

    updateInterior = (e) => {
        e.preventDefault();
        let interior = {
            interiorId: this.state.id,
            serialNum: this.state.serialNum,
            name: this.state.name
        };
        console.log('interior => ' + JSON.stringify(interior));
        console.log('id => ' + JSON.stringify(this.state.id));
        InteriorService.updateInterior(interior).then( res => {
            this.props.history.push('/interiors');
        });
    }

    changeserialNumHandler= (event) => {
        this.setState({serialNum: event.target.value});
    }
    changenameHandler= (event) => {
        this.setState({name: event.target.value});
    }

    cancel(){
        this.props.history.push('/interiors');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update Interior</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> serialNum: </label>
                                                <input placeholder="serialNum" name="serialNum" className="form-control" value={this.state.serialNum} onChange={this.changeserialNumHandler}/>

                                            <label> name: </label>
                                                <input placeholder="name" name="name" className="form-control" value={this.state.name} onChange={this.changenameHandler}/>

                                        </div>
                                        <button className="btn btn-success" onClick={this.updateInterior}>Save</button>
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

export default UpdateInteriorComponent
