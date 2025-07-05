import React, { Component } from 'react'
import InteriorService from '../services/InteriorService'

class ListInteriorComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                interiors: []
        }
        this.addInterior = this.addInterior.bind(this);
        this.editInterior = this.editInterior.bind(this);
        this.deleteInterior = this.deleteInterior.bind(this);
    }

    deleteInterior(id){
        InteriorService.deleteInterior(id).then( res => {
            this.setState({interiors: this.state.interiors.filter(interior => interior.interiorId !== id)});
        });
    }
    viewInterior(id){
        this.props.history.push(`/view-interior/${id}`);
    }
    editInterior(id){
        this.props.history.push(`/add-interior/${id}`);
    }

    componentDidMount(){
        InteriorService.getInteriors().then((res) => {
            this.setState({ interiors: res.data});
        });
    }

    addInterior(){
        this.props.history.push('/add-interior/_add');
    }

    render() {
        return (
            <div>
                 <h2 className="text-center">Interior List</h2>
                 <div className = "row">
                    <button className="btn btn-primary btn-sm" onClick={this.addInterior}> Add Interior</button>
                 </div>
                 <br></br>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> SerialNum </th>
                                    <th> Name </th>
                                    <th> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.interiors.map(
                                        interior => 
                                        <tr key = {interior.interiorId}>
                                             <td> { interior.serialNum } </td>
                                             <td> { interior.name } </td>
                                             <td>
                                                 <button onClick={ () => this.editInterior(interior.interiorId)} className="btn btn-outlie-info btn-sm">Update </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteInterior(interior.interiorId)} className="btn btn-danger btn-sm">Delete </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.viewInterior(interior.interiorId)} className="btn btn-outline-info btn-sm">View </button>
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

export default ListInteriorComponent
