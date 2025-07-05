import React, { Component } from 'react'
import TransmissionService from '../services/TransmissionService'

class ViewTransmissionComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            transmission: {}
        }
    }

    componentDidMount(){
        TransmissionService.getTransmissionById(this.state.id).then( res => {
            this.setState({transmission: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View Transmission Details</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <div className = "col" style={{textAlign:"right"}}><label> name:&emsp; </label></div>
                            <div className = "col" style={{textAlign:"left"}}> { this.state.transmission.name }</div>
                        </div>
                        <div className = "row">
                            <div className = "col" style={{textAlign:"right"}}><label> serialNum:&emsp; </label></div>
                            <div className = "col" style={{textAlign:"left"}}> { this.state.transmission.serialNum }</div>
                        </div>
                        <div className = "row">
                            <div className = "col" style={{textAlign:"right"}}><label> Type:&emsp; </label></div>
                            <div className = "col" style={{textAlign:"left"}}> { this.state.transmission.type }</div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewTransmissionComponent
