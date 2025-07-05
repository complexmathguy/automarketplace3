import React, { Component } from 'react'
import BodyService from '../services/BodyService'

class ViewBodyComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            body: {}
        }
    }

    componentDidMount(){
        BodyService.getBodyById(this.state.id).then( res => {
            this.setState({body: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View Body Details</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <div className = "col" style={{textAlign:"right"}}><label> name:&emsp; </label></div>
                            <div className = "col" style={{textAlign:"left"}}> { this.state.body.name }</div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewBodyComponent
