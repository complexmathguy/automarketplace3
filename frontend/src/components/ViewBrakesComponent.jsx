import React, { Component } from 'react'
import BrakesService from '../services/BrakesService'

class ViewBrakesComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            brakes: {}
        }
    }

    componentDidMount(){
        BrakesService.getBrakesById(this.state.id).then( res => {
            this.setState({brakes: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View Brakes Details</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <div className = "col" style={{textAlign:"right"}}><label> serialNum:&emsp; </label></div>
                            <div className = "col" style={{textAlign:"left"}}> { this.state.brakes.serialNum }</div>
                        </div>
                        <div className = "row">
                            <div className = "col" style={{textAlign:"right"}}><label> name:&emsp; </label></div>
                            <div className = "col" style={{textAlign:"left"}}> { this.state.brakes.name }</div>
                        </div>
                        <div className = "row">
                            <div className = "col" style={{textAlign:"right"}}><label> Type:&emsp; </label></div>
                            <div className = "col" style={{textAlign:"left"}}> { this.state.brakes.type }</div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewBrakesComponent
