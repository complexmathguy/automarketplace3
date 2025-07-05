import React, { Component } from 'react'
import BrakesService from '../services/BrakesService'

class ListBrakesComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                brakess: []
        }
        this.addBrakes = this.addBrakes.bind(this);
        this.editBrakes = this.editBrakes.bind(this);
        this.deleteBrakes = this.deleteBrakes.bind(this);
    }

    deleteBrakes(id){
        BrakesService.deleteBrakes(id).then( res => {
            this.setState({brakess: this.state.brakess.filter(brakes => brakes.brakesId !== id)});
        });
    }
    viewBrakes(id){
        this.props.history.push(`/view-brakes/${id}`);
    }
    editBrakes(id){
        this.props.history.push(`/add-brakes/${id}`);
    }

    componentDidMount(){
        BrakesService.getBrakess().then((res) => {
            this.setState({ brakess: res.data});
        });
    }

    addBrakes(){
        this.props.history.push('/add-brakes/_add');
    }

    render() {
        return (
            <div>
                 <h2 className="text-center">Brakes List</h2>
                 <div className = "row">
                    <button className="btn btn-primary btn-sm" onClick={this.addBrakes}> Add Brakes</button>
                 </div>
                 <br></br>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> SerialNum </th>
                                    <th> Name </th>
                                    <th> Type </th>
                                    <th> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.brakess.map(
                                        brakes => 
                                        <tr key = {brakes.brakesId}>
                                             <td> { brakes.serialNum } </td>
                                             <td> { brakes.name } </td>
                                             <td> { brakes.type } </td>
                                             <td>
                                                 <button onClick={ () => this.editBrakes(brakes.brakesId)} className="btn btn-outlie-info btn-sm">Update </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteBrakes(brakes.brakesId)} className="btn btn-danger btn-sm">Delete </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.viewBrakes(brakes.brakesId)} className="btn btn-outline-info btn-sm">View </button>
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

export default ListBrakesComponent
