import React, { Component } from 'react'
import ChassisService from '../services/ChassisService'

class ViewChassisComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            chassis: {}
        }
    }

    componentDidMount(){
        ChassisService.getChassisById(this.state.id).then( res => {
            this.setState({chassis: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View Chassis Details</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <div className = "col" style={{textAlign:"right"}}><label> name:&emsp; </label></div>
                            <div className = "col" style={{textAlign:"left"}}> { this.state.chassis.name }</div>
                        </div>
                        <div className = "row">
                            <div className = "col" style={{textAlign:"right"}}><label> serialNum:&emsp; </label></div>
                            <div className = "col" style={{textAlign:"left"}}> { this.state.chassis.serialNum }</div>
                        </div>
                        <div className = "row">
                            <div className = "col" style={{textAlign:"right"}}><label> Type:&emsp; </label></div>
                            <div className = "col" style={{textAlign:"left"}}> { this.state.chassis.type }</div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewChassisComponent
