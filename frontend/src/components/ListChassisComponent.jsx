import React, { Component } from 'react'
import ChassisService from '../services/ChassisService'

class ListChassisComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                chassiss: []
        }
        this.addChassis = this.addChassis.bind(this);
        this.editChassis = this.editChassis.bind(this);
        this.deleteChassis = this.deleteChassis.bind(this);
    }

    deleteChassis(id){
        ChassisService.deleteChassis(id).then( res => {
            this.setState({chassiss: this.state.chassiss.filter(chassis => chassis.chassisId !== id)});
        });
    }
    viewChassis(id){
        this.props.history.push(`/view-chassis/${id}`);
    }
    editChassis(id){
        this.props.history.push(`/add-chassis/${id}`);
    }

    componentDidMount(){
        ChassisService.getChassiss().then((res) => {
            this.setState({ chassiss: res.data});
        });
    }

    addChassis(){
        this.props.history.push('/add-chassis/_add');
    }

    render() {
        return (
            <div>
                 <h2 className="text-center">Chassis List</h2>
                 <div className = "row">
                    <button className="btn btn-primary btn-sm" onClick={this.addChassis}> Add Chassis</button>
                 </div>
                 <br></br>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> Name </th>
                                    <th> SerialNum </th>
                                    <th> Type </th>
                                    <th> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.chassiss.map(
                                        chassis => 
                                        <tr key = {chassis.chassisId}>
                                             <td> { chassis.name } </td>
                                             <td> { chassis.serialNum } </td>
                                             <td> { chassis.type } </td>
                                             <td>
                                                 <button onClick={ () => this.editChassis(chassis.chassisId)} className="btn btn-outlie-info btn-sm">Update </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteChassis(chassis.chassisId)} className="btn btn-danger btn-sm">Delete </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.viewChassis(chassis.chassisId)} className="btn btn-outline-info btn-sm">View </button>
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

export default ListChassisComponent
