import React, { Component } from 'react'
import BodyService from '../services/BodyService';

class CreateBodyComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
                name: ''
        }
        this.changenameHandler = this.changenameHandler.bind(this);
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            BodyService.getBodyById(this.state.id).then( (res) =>{
                let body = res.data;
                this.setState({
                    name: body.name
                });
            });
        }        
    }
    saveOrUpdateBody = (e) => {
        e.preventDefault();
        let body = {
                bodyId: this.state.id,
                name: this.state.name
            };
        console.log('body => ' + JSON.stringify(body));

        // step 5
        if(this.state.id === '_add'){
            body.bodyId=''
            BodyService.createBody(body).then(res =>{
                this.props.history.push('/bodys');
            });
        }else{
            BodyService.updateBody(body).then( res => {
                this.props.history.push('/bodys');
            });
        }
    }
    
    changenameHandler= (event) => {
        this.setState({name: event.target.value});
    }

    cancel(){
        this.props.history.push('/bodys');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add Body</h3>
        }else{
            return <h3 className="text-center">Update Body</h3>
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

                                        </div>

                                        <button className="btn btn-outline-success" onClick={this.saveOrUpdateBody}>Save</button>
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

export default CreateBodyComponent
