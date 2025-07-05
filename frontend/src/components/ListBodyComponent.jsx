import React, { Component } from 'react'
import BodyService from '../services/BodyService'

class ListBodyComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                bodys: []
        }
        this.addBody = this.addBody.bind(this);
        this.editBody = this.editBody.bind(this);
        this.deleteBody = this.deleteBody.bind(this);
    }

    deleteBody(id){
        BodyService.deleteBody(id).then( res => {
            this.setState({bodys: this.state.bodys.filter(body => body.bodyId !== id)});
        });
    }
    viewBody(id){
        this.props.history.push(`/view-body/${id}`);
    }
    editBody(id){
        this.props.history.push(`/add-body/${id}`);
    }

    componentDidMount(){
        BodyService.getBodys().then((res) => {
            this.setState({ bodys: res.data});
        });
    }

    addBody(){
        this.props.history.push('/add-body/_add');
    }

    render() {
        return (
            <div>
                 <h2 className="text-center">Body List</h2>
                 <div className = "row">
                    <button className="btn btn-primary btn-sm" onClick={this.addBody}> Add Body</button>
                 </div>
                 <br></br>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> Name </th>
                                    <th> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.bodys.map(
                                        body => 
                                        <tr key = {body.bodyId}>
                                             <td> { body.name } </td>
                                             <td>
                                                 <button onClick={ () => this.editBody(body.bodyId)} className="btn btn-outlie-info btn-sm">Update </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteBody(body.bodyId)} className="btn btn-danger btn-sm">Delete </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.viewBody(body.bodyId)} className="btn btn-outline-info btn-sm">View </button>
                                             </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>

                 </div>

            </div>
        )
    }
}

export default ListBodyComponent
