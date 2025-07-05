import React, { Component } from 'react'
import InteriorService from '../services/InteriorService';

class CreateInteriorComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
                serialNum: '',
                name: ''
        }
        this.changeserialNumHandler = this.changeserialNumHandler.bind(this);
        this.changenameHandler = this.changenameHandler.bind(this);
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            InteriorService.getInteriorById(this.state.id).then( (res) =>{
                let interior = res.data;
                this.setState({
                    serialNum: interior.serialNum,
                    name: interior.name
                });
            });
        }        
    }
    saveOrUpdateInterior = (e) => {
        e.preventDefault();
        let interior = {
                interiorId: this.state.id,
                serialNum: this.state.serialNum,
                name: this.state.name
            };
        console.log('interior => ' + JSON.stringify(interior));

        // step 5
        if(this.state.id === '_add'){
            interior.interiorId=''
            InteriorService.createInterior(interior).then(res =>{
                this.props.history.push('/interiors');
            });
        }else{
            InteriorService.updateInterior(interior).then( res => {
                this.props.history.push('/interiors');
            });
        }
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

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add Interior</h3>
        }else{
            return <h3 className="text-center">Update Interior</h3>
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

                                        </div>

                                        <button className="btn btn-outline-success" onClick={this.saveOrUpdateInterior}>Save</button>
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

export default CreateInteriorComponent
