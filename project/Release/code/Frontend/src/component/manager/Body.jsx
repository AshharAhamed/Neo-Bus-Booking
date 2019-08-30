import React, {Component} from 'react';
import { Fade } from 'react-slideshow-image';

export default class AdminBody extends Component {
    constructor() {
        super();
        this.fadeProperties = {
            duration: 5000,
            transitionDuration: 500,
            infinite: false,
            indicators: true
        }
    }


    render() {
        return (
            <div style={{marginTop : "50px"}}>
                <Fade>
                    <div className="each-slide">
                        <div style={{marginLeft : "210px" , marginBottom : "100px"}}>

                            <div className="imageContainer">
                                <img src={ require('../../images/SLTB1.jpg')} alt="Image1" width="1500" height="700" style={{ display: "block", marginleft: "auto", marginRight: "auto"}}/>
                                <div className="centeredImageText">
                                    <p className="footer-center" style={{ color: "black", fontSize: "20px"}}><b>Quality Transport Wherever You Are</b><br/>In just 25 years, CTB has achieved amazing feats and emerged as one of Sri Lanka's great departments.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="each-slide">
                        <div className="imageContainer">
                            <div style={{marginLeft : "210px" , marginBottom : "100px"}}>
                                <img src={ require('../../images/SLTB2.jpg')} alt="Image2" width="1500" height="700" style={{ display: "block", marginleft: "auto", marginRight: "auto"}}/>
                                <div className="centeredImageText">
                                    <p className="footer-center" style={{ color: "black", fontSize: "20px"}}><b>First Change maker Transportation Service in the Country </b><br/>Recognised as the first and only Changemaker Department</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="each-slide">
                        <div className="imageContainer">
                            <div style={{marginLeft : "210px" , marginBottom : "100px"}}>
                                <img src={ require('../../images/SLTB3.jpg')} alt="Image3" width="1500" height="700" style={{ display: "block", marginleft: "auto", marginRight: "auto"}}/>
                                <div className="centeredImageText">
                                    <p className="footer-center" style={{ color: "black", fontSize: "20px"}}><b>Making a great contribution to the State sector </b><br/>Contributing over 200 million per year</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="each-slide">
                        <div className="imageContainer">
                            <div style={{marginLeft : "210px" , marginBottom : "100px"}}>
                                <img src={ require('../../images/SLTB4.jpg')} alt="Image4" width="1500" height="700" style={{ display: "block", marginleft: "auto", marginRight: "auto"}}/>
                                <div className="centeredImageText">
                                    <p className="footer-center" style={{ color: "black", fontSize: "20px"}}><b></b><br/>Celebrating 80 Years of Excellency</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="each-slide">
                        <div className="imageContainer">
                            <div style={{marginLeft : "210px" , marginBottom : "100px"}}>
                                <img src={ require('../../images/SLTB5.jpg')} alt="Image5" width="1500" height="700" style={{ display: "block", marginleft: "auto", marginRight: "auto"}}/>
                                <div className="centeredImageText">
                                    <p className="footer-center" style={{ color: "black", fontSize: "20px"}}><b>Quality is not an act, but a habit. – Aristotle.</b></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="each-slide">
                        <div className="imageContainer">
                            <div style={{marginLeft : "210px" , marginBottom : "100px"}}>
                                <img src={ require('../../images/SLTB6.jpg')} alt="Image6" width="1500" height="700" style={{ display: "block", marginleft: "auto", marginRight: "auto"}}/>
                                <div className="centeredImageText">
                                    <p className="footer-center" style={{ color: "black", fontSize: "20px"}}><b>Top Up your Credit Wherever you are</b></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="each-slide">
                        <div className="imageContainer">
                            <div style={{marginLeft : "210px" , marginBottom : "100px"}}>
                                <img src={ require('../../images/SLTB7.jpg')} alt="Image7" width="1500" height="700" style={{ display: "block", marginleft: "auto", marginRight: "auto"}}/>
                                <div className="centeredImageText">
                                    <p className="footer-center" style={{ color: "black", fontSize: "20px"}}><b>Make Your Ambitions a Reality</b><br/>Quality, Affordability, Trustworthy</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="each-slide">
                        <div className="imageContainer">
                            <div style={{marginLeft : "210px" , marginBottom : "100px"}}>
                                <img src={ require('../../images/SLTB8.jpg')} alt="Image8" width="1500" height="700" style={{ display: "block", marginleft: "auto", marginRight: "auto"}}/>
                                <div className="centeredImageText">
                                    <p className="footer-center" style={{ color: "black", fontSize: "20px"}}><b>E Ticketing System</b><br/>As Mobile and Web Client</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </Fade>
            </div>
        )
    };
}