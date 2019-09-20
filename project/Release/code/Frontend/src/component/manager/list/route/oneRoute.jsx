import React, {Component} from 'react'
import ManagerService from '../../../../services/ManagerService'

export default class OneRoute extends Component {
    constructor(props) {
        super(props);
        this.managerService = new ManagerService();
        this.delete = this.delete.bind(this);
        this.renderBusStation = this.renderBusStation.bind(this);
    }

    delete() {
        this.managerService.deleteJourney(this.props.obj.cardNo).then(response => {
            alert(response.data);
            window.location.reload();
        }).catch(err => {
            console.log(err)
        })
    }

    renderBusStation(){
        return this.props.obj.busHalts.map(function (object, i) {
            return <tr key={i} className="text-center">{object}</tr>;
        });
    }

    render() {
        return (
            <tr>
                <td>{this.props.obj.routeName}</td>
                <td>{this.props.obj.routeNo}</td>
                <td>{this.renderBusStation()}</td>
                <td>
                    <button onClick={this.delete} className="btn btn-danger">Delete  <i className="fa fa-trash"/></button>
                </td>
            </tr>
        )
    }
}