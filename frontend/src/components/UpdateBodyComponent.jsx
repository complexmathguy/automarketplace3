import React, { Component } from 'react'
import BodyService from '../services/BodyService';

class UpdateBodyComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
                name: ''
        }
        this.updateBody = this.updateBody.bind(this);

        this.changenameHandler = this.changenameHandler.bind(this);
    }

    componentDidMount(){
        BodyService.getBodyById(this.state.id).then( (res) =>{
            let body = res.data;
            this.setState({
                name: body.name
            });
        });
    }

    updateBody = (e) => {
        e.preventDefault();
        let body = {
            bodyId: this.state.id,
            name: this.state.name
        };
        console.log('body => ' + JSON.stringify(body));
        console.log('id => ' + JSON.stringify(this.state.id));
        BodyService.updateBody(body).then( res => {
            this.props.history.push('/bodys');
        });
    }

    changenameHandler= (event) => {
        this.setState({name: event.target.value});
    }

    cancel(){
        this.props.history.push('/bodys');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update Body</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> name: </label>
                                                <input placeholder="name" name="name" className="form-control" value={this.state.name} onChange={this.changenameHandler}/>

                                        </div>
                                        <button className="btn btn-success" onClick={this.updateBody}>Save</button>
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

export default UpdateBodyComponent
