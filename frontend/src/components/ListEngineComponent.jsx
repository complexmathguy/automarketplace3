import React, { Component } from 'react'
import EngineService from '../services/EngineService'

class ListEngineComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                engines: []
        }
        this.addEngine = this.addEngine.bind(this);
        this.editEngine = this.editEngine.bind(this);
        this.deleteEngine = this.deleteEngine.bind(this);
    }

    deleteEngine(id){
        EngineService.deleteEngine(id).then( res => {
            this.setState({engines: this.state.engines.filter(engine => engine.engineId !== id)});
        });
    }
    viewEngine(id){
        this.props.history.push(`/view-engine/${id}`);
    }
    editEngine(id){
        this.props.history.push(`/add-engine/${id}`);
    }

    componentDidMount(){
        EngineService.getEngines().then((res) => {
            this.setState({ engines: res.data});
        });
    }

    addEngine(){
        this.props.history.push('/add-engine/_add');
    }

    render() {
        return (
            <div>
                 <h2 className="text-center">Engine List</h2>
                 <div className = "row">
                    <button className="btn btn-primary btn-sm" onClick={this.addEngine}> Add Engine</button>
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
                                    this.state.engines.map(
                                        engine => 
                                        <tr key = {engine.engineId}>
                                             <td> { engine.name } </td>
                                             <td> { engine.serialNum } </td>
                                             <td> { engine.type } </td>
                                             <td>
                                                 <button onClick={ () => this.editEngine(engine.engineId)} className="btn btn-outlie-info btn-sm">Update </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteEngine(engine.engineId)} className="btn btn-danger btn-sm">Delete </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.viewEngine(engine.engineId)} className="btn btn-outline-info btn-sm">View </button>
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

export default ListEngineComponent
