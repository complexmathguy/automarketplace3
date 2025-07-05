import React, { Component } from 'react'
import InteriorService from '../services/InteriorService'

class ViewInteriorComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            interior: {}
        }
    }

    componentDidMount(){
        InteriorService.getInteriorById(this.state.id).then( res => {
            this.setState({interior: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View Interior Details</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <div className = "col" style={{textAlign:"right"}}><label> serialNum:&emsp; </label></div>
                            <div className = "col" style={{textAlign:"left"}}> { this.state.interior.serialNum }</div>
                        </div>
                        <div className = "row">
                            <div className = "col" style={{textAlign:"right"}}><label> name:&emsp; </label></div>
                            <div className = "col" style={{textAlign:"left"}}> { this.state.interior.name }</div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewInteriorComponent
