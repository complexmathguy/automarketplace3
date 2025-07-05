import React, { Component } from 'react'
import TransmissionService from '../services/TransmissionService'

class ListTransmissionComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                transmissions: []
        }
        this.addTransmission = this.addTransmission.bind(this);
        this.editTransmission = this.editTransmission.bind(this);
        this.deleteTransmission = this.deleteTransmission.bind(this);
    }

    deleteTransmission(id){
        TransmissionService.deleteTransmission(id).then( res => {
            this.setState({transmissions: this.state.transmissions.filter(transmission => transmission.transmissionId !== id)});
        });
    }
    viewTransmission(id){
        this.props.history.push(`/view-transmission/${id}`);
    }
    editTransmission(id){
        this.props.history.push(`/add-transmission/${id}`);
    }

    componentDidMount(){
        TransmissionService.getTransmissions().then((res) => {
            this.setState({ transmissions: res.data});
        });
    }

    addTransmission(){
        this.props.history.push('/add-transmission/_add');
    }

    render() {
        return (
            <div>
                 <h2 className="text-center">Transmission List</h2>
                 <div className = "row">
                    <button className="btn btn-primary btn-sm" onClick={this.addTransmission}> Add Transmission</button>
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
                                    this.state.transmissions.map(
                                        transmission => 
                                        <tr key = {transmission.transmissionId}>
                                             <td> { transmission.name } </td>
                                             <td> { transmission.serialNum } </td>
                                             <td> { transmission.type } </td>
                                             <td>
                                                 <button onClick={ () => this.editTransmission(transmission.transmissionId)} className="btn btn-outlie-info btn-sm">Update </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteTransmission(transmission.transmissionId)} className="btn btn-danger btn-sm">Delete </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.viewTransmission(transmission.transmissionId)} className="btn btn-outline-info btn-sm">View </button>
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

export default ListTransmissionComponent
