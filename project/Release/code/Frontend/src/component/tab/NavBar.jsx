import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import UserService from '../../services/UserService'
import Clock from "react-digital-clock";

export default class NavigationBar extends Component {
    constructor(props) {
        super(props);
        this.userService = new UserService();
        this.state = {
            isLoggedIn: this.userService.isLoggedIn,
            userType: this.userService.Type
        };
    }


    render() {
        return (
            <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                {
                    (this.state.isLoggedIn && this.state.userType === "Manager") ? (
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item active">
                                <Link className="nav-link" to="/managerHome">Home</Link>
                            </li>

                            <li className="nav-item">
                                <Link className="nav-link" to="/managePassengers">Manage Passengers</Link>
                            </li>

                            <li className="nav-item">
                                <Link className="nav-link" to="/manageAdmins">Manage Admins</Link>
                            </li>

                            <li className="nav-item">
                                <Link className="nav-link" to="/">Manage Routes</Link>
                            </li>

                            <li className="nav-item">
                                <Link className="nav-link" to="/">Analyze Statistical Data</Link>
                            </li>

                            <div className="row top-buffer">
                                <div className="col">
                                    <div className="dropdown">
                                        <Link className="nav-link dropdown-toggle" to="#" data-toggle="dropdown">
                                            Passenger Services
                                        </Link>
                                        <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            <Link className="dropdown-item" to="/">Issue Card</Link>
                                            <Link className="dropdown-item" to="/">Account Top-Up</Link>
                                            <Link className="dropdown-item" to="/">Recover Card</Link>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="form-inline my-2 my-lg-0" style={{marginLeft: 600}}>
                                <ul className="navbar-nav mr-auto">
                                    <div className="navbar-brand" onClick={this.userService.logout}>Log Out <i
                                        className="fa fa-sign-out" style={{margin: '12px'}}/>
                                    </div>
                                </ul>
                            </div>
                        </ul>
                    ) : (this.state.isLoggedIn && this.state.userType === "Passenger") ? (
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item active">
                                <Link className="nav-link" to="/">Home</Link>
                            </li>

                            <li className="nav-item">
                                <Link className="nav-link" to="/">Profile</Link>
                            </li>

                            <li className="nav-item">
                                <Link className="nav-link" to="/">Top-Up</Link>
                            </li>

                            <li className="nav-item">
                                <Link className="nav-link" to="/">Recover Card</Link>
                            </li>

                            <div className="form-inline my-2 my-lg-0">
                                <ul className="navbar-nav mr-auto">
                                    <div className="navbar-brand" onClick={this.userService.logout}>Log Out <i
                                        className="fa fa-sign-out" style={{margin: '12px'}}/>
                                    </div>
                                </ul>
                            </div>
                        </ul>
                    ) : null
                }
                <div style={{marginRight: "10px"}}>
                    <Clock/>
                </div>
            </nav>)
    };
}