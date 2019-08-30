import React, {Component} from 'react';

export default class Footer extends Component {

    render() {
        return <div>
            <div className="footer-distributed">
                <div className="footer-left">
                    <h3>Sri Lanka Transport Board<span><img src={ require('../../images/SLTBLogo.png')} alt="Image1" width="100" height="100" style={{ display: "block", marginleft: "auto", marginRight: "auto"}}/></span></h3>
                    <p className="footer-company-name">Sri Lanka Transportation Board &copy; 2019</p>
                </div>

                <div className="footer-center">

                    <div>
                        <i className="fa fa-map-marker"/>
                        <p><span> Bernard Soysa Mawatha </span> Colombo 05</p>
                    </div>

                    <div>
                        <i className="fa fa-phone"/>
                        <p>011 2 581 120</p>
                    </div>

                    <div>
                        <i className="fa fa-envelope"/>
                        <p><a href="mailto:woodcreakuniversity@gmail.com">info@sltb.lk</a></p>
                    </div>

                </div>

                <div className="footer-right">

                    <p className="footer-company-about">
                        <span>OUR VISION</span>
                        To provide the public a safe, dependable and comfortable road passenger transport at a reasonable fare system through a staff dedicated to service and obtain the optimum utilization of all resources functioning as a financially viable organization.<br/><br/>
                        <span>OUR MISSION</span>
                        To be the excellent provider of passenger transport.
                    </p>

                    <div className="footer-icons">
                        <a href="https://www.facebook.com/SLTBlk/"><i className="fa fa-facebook"/></a>
                        <a href="https://twitter.com/tmsltb"><i className="fa fa-twitter"/></a>
                        <a href="https://www.instagram.com/explore/tags/sltb/?hl=en"><i className="fa fa-instagram"/></a>
                        <a href="https://www.youtube.com/watch?reload=9&v=khRPdjh1fGo"><i className="fa fa-youtube-play"/></a>
                    </div>
                </div>
            </div>
        </div>;
    }
}