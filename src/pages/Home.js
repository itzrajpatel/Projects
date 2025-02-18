import React from "react";
import { Link } from "react-router-dom";
import { useEffect, useState } from 'react';
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min";
import "../App.css";
import "../styles/Button.css";
import logo from "../assets/logo.png"; // Importing the logo
import avatar from "../assets/avatar.png"; // Importing the avatar
import pic7 from "../assets/pic-7.jpg";
import photo1 from "../assets/photo-1.jpg"; // Import images from src/assets
import photo2 from "../assets/photo-2.jpg";
import photo3 from "../assets/photo-3.jpg";
import photo4 from "../assets/podium.png";
import photo5 from "../assets/racket.png";
import photo6 from "../assets/ball.png";
import photo7 from "../assets/badge.png";
import photo8 from "../assets/pic-1.jpg";
import photo9 from "../assets/pic-2.jpg";
import photo10 from "../assets/pic-3.jpg";
import photo11 from "../assets/pic-4.jpg";
import photo12 from "../assets/pic-5.jpg";
import photo13 from "../assets/pic-6.jpg";
import photo14 from "../assets/play_icon.png";


const Home = () => {
  const events = [
    {
  date: "08",
  month: "Dec, 2024",
  image: photo9,
  title: "PROFESSIONAL TENNIS GAME SETS",
  location: "Chicago",
  startTime: "Dec 8, 2024 | 8:00 am",
  endTime: "Dec 22, 2024 | 4:30 pm",
},
{
  date: "06",
  month: "Jan, 2024",
  image: photo10,
  title: "PROFESSIONAL TENNIS GAME SETS",
  location: "Chicago",
  startTime: "Jan 6, 2024 | 10:00 am",
  endTime: "Jan 23, 2024 | 5:00 pm",
},
{
  date: "10",
  month: "Feb, 2024",
  image: photo13,
  title: "PROFESSIONAL TENNIS GAME SETS",
  location: "Chicago",
  startTime: "Feb 10, 2024 | 3:30 pm",
  endTime: "Feb 22, 2024 | 7:00 pm",
},
];

  const [courts, setCourts] = useState(0);
  const [coaches, setCoaches] = useState(0);
  const [years, setYears] = useState(0);
  const [clubs, setClubs] = useState(0);
  const [articles, setArticles] = useState([]);
  const [loggedInUser, setLoggedInUser] = useState(null);

  useEffect(() => {
    const duration = 5000; // 5 seconds
    const steps = 100; // Number of updates
    const intervalTime = duration / steps; 

    const animateNumber = (target, setter) => {
      let count = 0;
      const increment = target / steps;

      const interval = setInterval(() => {
        count += increment;
        if (count >= target) {
          setter(target);
          clearInterval(interval);
        } else {
          setter(Math.floor(count));
        }
      }, intervalTime);
    };

    animateNumber(98, setCourts);
    animateNumber(65, setCoaches);
    animateNumber(10, setYears);
    animateNumber(15, setClubs);

    const fetchNews = async () => {
      try {
        const response = await fetch(
          `https://newsapi.org/v2/everything?q=tennis&apiKey=c70d9ebe8ffe4a009bccbe7659a636d7`
        );
        const data = await response.json();
        
        console.log("API Response:", data); // Debugging
        
        if (data.status === "error") {
          console.error("API Error:", data.message);
          return;
        }
        
        setArticles(data?.articles?.slice(0, 3) || []);
        
      } catch (error) {
        console.error("Error fetching news:", error);
      }
    };
  
    fetchNews();

    const user = localStorage.getItem("loggedInUser");
    if (user) {
      setLoggedInUser(user);
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("loggedInUser");
    setLoggedInUser(null);
  };
  return (
    <>
      {/* <--- NAVBAR --->*/}
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container-fluid" style={{ gap: "15px" }}>
        <img alt="Logo" src={logo} height="50px" width="50px" style={{ margin: "0 20px" }} />
        <a className="navbar-brand" href="/" style={{ fontFamily: "Ovo, serif", fontSize: "19px", fontWeight: "bold" }}>
          Indian Tennis Academy
        </a>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav ms-auto" style={{ gap: "12px" }}>
            <li className="nav-item">
              <Link className="nav-link" to="/">Home</Link>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#events">Events</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#blogs">Blogs</a>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/shop">Shop</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/contact">Contact</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/orders">Orders</Link>
            </li>
            
            {/* Conditionally render Login or Profile Image */}
            {!loggedInUser ? (
              <li className="nav-item">
                <Link className="nav-link" to="/login">Login</Link>
              </li>
            ) : (
              <li className="nav-item dropdown">
                <img 
                  src={avatar} 
                  className="rounded-circle mt-2" 
                  alt="Profile" 
                  width="30" 
                  height="30" 
                  data-bs-toggle="dropdown" 
                  aria-expanded="false" 
                  style={{ cursor: "pointer" }}
                />
                <ul className="dropdown-menu dropdown-menu-end mt-3 bg-dark">
                  <li>
                    <button id="logoutbtn" className="dropdown-item text-light" onClick={handleLogout}>
                      Logout
                    </button>
                  </li>
                </ul>
              </li>
            )}
          </ul>
        </div>
      </div>
    </nav>

      
      <div id="carouselExampleIndicators" className="carousel slide" data-bs-ride="carousel">
      {/* Carousel Indicators */}
      <div className="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" className="active"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"></button>
      </div>

      {/* Carousel Items */}
      <div className="carousel-inner">
        {/* Slide 1 */}
        <div className="carousel-item active">
          <div className="d-flex justify-content-center align-items-center" style={{ height: "700px", backgroundColor: "#6c757d", color: "white" }}>
            <img src={photo1} width="100%" height="100%" alt="Slide 1" />
            <div className="text-center" style={{
              zIndex: 1, position: "absolute", top: "50%", left: "35%",
              transform: "translate(-50%, -50%)", color: "white", fontSize: "50px"
            }}>
            <h1 className="text-center fw-bold" style={{ fontFamily: "Cormorant Garamond, serif", lineHeight: "1.7", marginTop: "100px" }}>
                FIND YOUR <br />
                BEST SPORT <br />
                MOTIVATION
            </h1>
            </div>
          </div>
        </div>

        {/* Slide 2 */}
        <div className="carousel-item">
          <div className="d-flex justify-content-center align-items-center" style={{ height: "700px", backgroundColor: "#6c757d", color: "white" }}>
            <img src={photo2} width="100%" height="100%" alt="Slide 2" />
            <div className="text-center" style={{
              zIndex: 1, position: "absolute", top: "70%", left: "30%",
              transform: "translate(-50%, -50%)", color: "white", fontSize: "40px"
            }}>
            <h1 className="text-center fw-bold" style={{ fontFamily: "Cormorant Garamond, serif", lineHeight: "1.7", marginTop: "100px" }}>
                TEAM WORK <br />
                MAKES THE <br />
                DREAM WORK
            </h1>
            </div>
          </div>
        </div>

        {/* Slide 3 */}
        <div className="carousel-item">
          <div className="d-flex justify-content-center align-items-center" style={{ height: "700px", backgroundColor: "#6c757d", color: "white" }}>
            <img src={photo3} width="100%" height="100%" alt="Slide 3" />
            <div className="text-center" style={{
              zIndex: 1, position: "absolute", top: "70%", left: "30%",
              transform: "translate(-50%, -50%)", color: "white", fontSize: "40px"
            }}>
            <h1 className="text-center fw-bold" style={{ fontFamily: "Cormorant Garamond, serif", lineHeight: "1.7", marginTop: "100px" }}>
                SERVE IT <br />
                SMASH IT <br />
                WIN IT
            </h1>
            </div>
          </div>
        </div>
      </div>

      {/* Controls */}
      <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
      </button>
      <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
        <span className="carousel-control-next-icon" aria-hidden="true"></span>
      </button>
    </div>

    {/* <--- SECTION-1 ---> */}
    <div className="container-fluid mb-5" style={{ backgroundColor: "#f3f8f9" }}>
      <div className="row text-center">
        {/* OUR CLUB */}
        <div className="col-md-3 mt-4">
          <div className="p-3">
            <div className="rounded-circle mx-auto d-flex justify-content-center align-items-center"
                 style={{ width: "80px", height: "80px" }}>
              <img src={photo5} className="img-fluid" width="70px" height="70px" alt="Racket" />
            </div>
            <h4 className="mt-3" style={{ fontFamily: "Cormorant Garamond, serif" }}>OUR CLUB</h4>
            <p className="text-dark">Consectetur adipiscing elit, sed do euismod tempo.</p>
            <button className="btn mb-5">READ MORE</button>
          </div>
        </div>

        {/* COACHING */}
        <div className="col-md-3 mt-4">
          <div className="p-3">
            <div className="rounded-circle mx-auto d-flex justify-content-center align-items-center"
                 style={{ width: "80px", height: "80px" }}>
              <img src={photo4} width="70px" height="70px" alt="Podium" />
            </div>
            <h4 className="mt-3" style={{ fontFamily: "Cormorant Garamond, serif" }}>COACHING</h4>
            <p className="text-dark">Consectetur adipiscing elit, sed do euismod tempo.</p>
            <button className="btn mb-5">READ MORE</button>
          </div>
        </div>

        {/* OUR STORE */}
        <div className="col-md-3 mt-4">
          <div className="p-3">
            <div className="rounded-circle mx-auto d-flex justify-content-center align-items-center"
                 style={{ width: "80px", height: "80px" }}>
              <img src={photo6} width="70px" height="70px" alt="Ball" />
            </div>
            <h4 className="mt-3" style={{ fontFamily: "Cormorant Garamond, serif" }}>OUR STORE</h4>
            <p className="text-dark">Consectetur adipiscing elit, sed do euismod tempo.</p>
            <button className="btn mb-5">READ MORE</button>
          </div>
        </div>

        {/* CONTACT US */}
        <div className="col-md-3 mt-4">
          <div className="p-3">
            <div className="rounded-circle mx-auto d-flex justify-content-center align-items-center" style={{ width: "80px", height: "80px" }}>
              <img src={photo7} width="70px" height="70px" alt="Badge" />
            </div>
            <h4 className="mt-3" style={{ fontFamily: "Cormorant Garamond, serif" }}>CONTACT US</h4>
            <p className="text-dark">Consectetur adipiscing elit, sed do euismod tempo.</p>
            <button className="btn mb-5">READ MORE</button>
          </div>
        </div>
      </div>
    </div>

    {/* <--- SECTION-2 ---> */}
    <div className="container-fluid my-5">
      <div className="row align-items-center">
        {/* LEFT IMAGE SECTION */}
        <div className="col-md-6 my-5">
          <div className="bg-light text-center position-relative mx-auto" style={{ width: "90%", height: "750px" }}>
            <img src={photo8} alt="Image not found!" width="100%" height="100%" />
            {/* Dark overlay text box */}
            <div className="bg-dark text-center text-light position-absolute d-flex align-items-center justify-content-center" style={{ width: "300px", height: "150px", bottom: "0", right: "0", fontSize: "22px", fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif", fontWeight: "bolder", padding: "2em" }}> OUR COURTS MAKE THE WORLD'S TOP 10 </div>
          </div>
        </div>

        {/* RIGHT TEXT SECTION */}
        <div className="col-md-6" style={{ padding: "50px" }}>
          <h1 style={{ fontWeight: "bolder", fontFamily: "Ovo, serif" }}>WE WELCOME YOU AT</h1>
          <h1 style={{ fontWeight: "bolder", fontFamily: "Ovo, serif" }}>OUR CLUB</h1>
          <p className="text-dark" style={{ fontSize: "24px", marginTop: "20px" }}>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
            In massa tempor nec feugiat nisl pretium.
          </p>
          <p className="text-dark" style={{ fontSize: "24px", marginTop: "50px" }}>
            Tempor orci eu lobortis elementum nibh tellus. Eget dolor morbi non arcu risus quis varius quam.
          </p>
          <button type="button" className="btn mx-5" style={{ marginTop: "50px", backgroundColor: "#2eac6d", color: "aliceblue", height: "50px", width: "200px" }}> JOIN OUR CLUB </button>
        </div>
      </div>
    </div>

    {/* <--- SECTION-3 ---> */}
    <div className="container-fluid my-5">
      <div className="row align-items-center">
        {/* COURTS */}
        <div className="col-sm-6 col-md-3">
          <div className="text-center position-relative" style={{ width: "100%", height: "200px" }}>
            <h1 style={{ fontSize: "150px", fontFamily: "Oswald, sans-serif", color: "#e4eaeb", fontWeight: "bolder" }}>
              {courts}
            </h1>
            <div className="text-dark text-center position-absolute" style={{ width: "100%", height: "30px", bottom: "90px", left: "50%", transform: "translateX(-50%)", fontSize: "22px", fontFamily: "Oswald, sans-serif", fontWeight: "bold" }}>
              COURTS
            </div>
          </div>
        </div>

        {/* COACHES */}
        <div className="col-sm-6 col-md-3">
          <div className="text-center position-relative" style={{ width: "100%", height: "200px" }}>
            <h1 style={{ fontSize: "150px", fontFamily: "Oswald, sans-serif", color: "#e4eaeb", fontWeight: "bolder" }}>
              {coaches}
            </h1>
            <div className="text-dark text-center position-absolute" style={{ width: "100%", height: "30px", bottom: "90px", left: "50%", transform: "translateX(-50%)", fontSize: "22px", fontFamily: "Oswald, sans-serif", fontWeight: "bold" }}>
              COACHES
            </div>
          </div>
        </div>

        {/* YEARS */}
        <div className="col-sm-6 col-md-3">
          <div className="text-center position-relative" style={{ width: "100%", height: "200px" }}>
            <h1 style={{ fontSize: "150px", fontFamily: "Oswald, sans-serif", color: "#e4eaeb", fontWeight: "bolder" }}>
              {years}
            </h1>
            <div className="text-dark text-center position-absolute" style={{ width: "100%", height: "30px", bottom: "90px", left: "50%", transform: "translateX(-50%)", fontSize: "22px", fontFamily: "Oswald, sans-serif", fontWeight: "bold" }}>
              YEARS
            </div>
          </div>
        </div>

        {/* CLUBS */}
        <div className="col-sm-6 col-md-3">
          <div className="text-center position-relative" style={{ width: "100%", height: "200px" }}>
            <h1 style={{ fontSize: "150px", fontFamily: "Oswald, sans-serif", color: "#e4eaeb", fontWeight: "bolder" }}>
              {clubs}
            </h1>
            <div className="text-dark text-center position-absolute" style={{ width: "100%", height: "30px", bottom: "90px", left: "50%", transform: "translateX(-50%)", fontSize: "22px", fontFamily: "Oswald, sans-serif", fontWeight: "bold" }}>
              CLUBS
            </div>
          </div>
        </div>
      </div>
    </div>

    {/* <--- SECTION-4 ---> */}
    <div id="container" className="container-fluid my-5">
        <div id="image-container" className="box span-row" style={{ backgroundColor: "#03bfb1", color: "white" }}>
            <img src={photo11} height="100%" width="100%" />
        </div>
        <div id="image-container" className="box span-row" style={{ backgroundColor: "#33c361", color: "white" }}>
            <img src={photo10} height="100%" width="100%" />
        </div>
        <div id="image-container" className="box span-row2" style={{ backgroundColor: "#6edb6e", color: "white" }}>
            <img src={photo9} height="100%" width="100%" />
        </div>
        <div id="image-container" className="box span-row3" style={{ backgroundColor: "#0f98c6", color: "white" }}>
            <img src={photo13} height="100%" width="100%" />
        </div>
        <div id="image-container" className="box span-row3" style={{ backgroundColor: "#005b92", color: "white" }}>
            <img src={photo12} height="100%" width="100%" />
        </div>
    </div>

    {/* <--- SECTION-5 ---> */}
    <div className="container-fluid my-5 p-5" style={{ backgroundColor: "#f3f8f9" }}>
      <div className="row text-center">
        {/* GROUP MEMBERSHIP */}
        <div className="col-md-3">
          <div className="p-4">
            <div className="bg-white text-dark text-center mx-auto p-3 shadow-lg rounded" style={{ height: "100%", width: "auto" }}>
              <h1 className="py-3" style={{ fontSize: "25px", fontFamily: "Oswald, sans-serif" }}>GROUP</h1>
              <p className="text-muted" style={{ fontSize: "19px" }}>Lorem ipsum dolor</p>
              <h1 style={{ fontSize: "60px", fontFamily: "Oswald, sans-serif", marginTop: "50px" }}>
                <sup style={{ fontSize: "0.6em" }}>$</sup> 59 <span className="text-muted" style={{ fontSize: "30px" }}>/MO</span>
              </h1>
              <p className="text-muted mt-3">Elis tempor</p>
              <p className="text-muted mt-2">Adipiscing consect</p>
              <p className="text-muted mt-2">Voluptatem quia voluptas</p>
              <button type="button" className="btn btn-success mt-4" style={{ height: "auto", width: "auto" }}>GET NOW</button>
            </div>
          </div>
        </div>

        {/* PERSONAL MEMBERSHIP */}
        <div className="col-md-3">
          <div className="p-4">
            <div className="bg-white text-dark text-center mx-auto p-3 shadow-lg rounded" style={{ height: "100%", width: "auto" }}>
              <h1 className="py-3" style={{ fontSize: "25px", fontFamily: "Oswald, sans-serif" }}>PERSONAL</h1>
              <p className="text-muted" style={{ fontSize: "19px" }}>Lorem ipsum dolor</p>
              <h1 style={{ fontSize: "60px", fontFamily: "Oswald, sans-serif", marginTop: "50px" }}>
                <sup style={{ fontSize: "0.6em" }}>$</sup> 99 <span className="text-muted" style={{ fontSize: "30px" }}>/MO</span>
              </h1>
              <p className="text-muted mt-3">Elis tempor</p>
              <p className="text-muted mt-2">Adipiscing consect</p>
              <p className="text-muted mt-2">Voluptatem quia voluptas</p>
              <button type="button" className="btn btn-dark mt-4" style={{ height: "auto", width: "auto" }}>GET NOW</button>
            </div>
          </div>
        </div>

        {/* MEMBERSHIP INFO & VIDEO */}
        <div className="col-md-6 d-flex flex-column align-items-center justify-content-center text-center p-4">
          <h4 className="text-uppercase" style={{ fontSize: "20px", fontFamily: "Lucida Sans, sans-serif", letterSpacing: "3px", marginTop: "20px" }}>Our Plans</h4>
          <h1 className="my-4" style={{ fontFamily: "Oswald, sans-serif" }}>Club Membership</h1>
          <p className="text-muted">Adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim.</p>

          {/* Video Section */}
          <div className="my-4 d-flex justify-content-center align-items-center p-3 rounded" style={{ cursor: "pointer" }}>
            <div className="rounded-circle d-flex justify-content-center align-items-center border" style={{ width: "50px", height: "50px", marginRight: "15px" }}>
              <img src={photo14} width="30px" height="30px" alt="Play Icon" />
            </div>
            <p className="mb-0 text-dark" style={{ fontFamily: "Oswald, sans-serif" }}>View Video Presentation</p>
          </div>
        </div>
      </div>
    </div>

    {/* <--- SECTION-6 --> */}
    <div id="events" className="container-fluid my-5">
        <div className="row text-center">
          {/* Event Title Section */}
          <div className="col-md-3">
            <div className="p-4">
              <div
                className="text-dark text-center mx-auto"
                style={{ width: "auto", height: "400px" }}
              >
                <h1
                  className="mx-auto"
                  style={{
                    paddingTop: "4em",
                    fontSize: "20px",
                    fontFamily:
                      "'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif",
                    letterSpacing: "5px",
                  }}
                >
                  GAMES CALENDAR
                </h1>
                <h1
                  className="mx-auto"
                  style={{
                    paddingTop: "2em",
                    fontSize: "50px",
                    fontFamily: "Oswald, sans-serif",
                  }}
                >
                  FUTURE EVENTS
                </h1>
              </div>
            </div>
          </div>

          {/* Event List */}
          <div className="col-md-9">
            <div className="p-5">
              <p className="mb-5 text-dark">
                Dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
                aspernatur aut odit aut fugit, sed quia consequuntur. Dicta sunt
                explicabo. Nemo enim ipsam voluptatem quia voluptas.
              </p>

              {events.map((event, index) => (
                <div key={index}>
                  <div className="row g-0">
                    <div className="text-center col-md-3 d-flex flex-column align-items-start justify-content-center">
                      <h1 className="mx-auto" style={{ fontFamily: "Fira Sans, sans-serif" }}>
                        {event.date}
                      </h1>
                      <p className="mx-auto">{event.month}</p>
                    </div>
                    <div className="col-md-3">
                      <img
                        src={event.image}
                        height="170px"
                        width="100%"
                        className="my-5"
                        style={{ display: "block" }}
                        alt="Event"
                      />
                    </div>
                    <div className="col-md-6 text-center p-5 my-5">
                      <h1 style={{ fontSize: "20px", fontFamily: "Oswald, sans-serif" }}>
                        {event.title}
                      </h1>
                      <p className="my-4 text-muted">{event.location}</p>
                      <p className="text-muted">{event.startTime} - {event.endTime}</p>
                    </div>
                  </div>
                  {index < events.length - 1 && <hr />}
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>

      {/* <--- SECTION-7 ---> */}
      <div className="container-fluid my-5">
      <div className="row text-center">
        <div className="col-md-6 mb-5">
          <img src={pic7} width="100%" alt="Contact Us" />
        </div>
        <div className="col-md-6">
          <div className="p-3">
            <h4 className="mx-auto mb-5" style={{ fontSize: "20px", fontFamily: "'Lucida Sans', Geneva, Verdana, sans-serif", letterSpacing: "5px", marginTop: "100px" }}>
              CONTACT US
            </h4>
            <h1 className="mx-auto" style={{ fontFamily: "Oswald, sans-serif" }}>
              HAVE QUESTIONS?
            </h1>
            <h1 className="mx-auto" style={{ fontFamily: "Oswald, sans-serif" }}>
              GET IN TOUCH!
            </h1>
            <div className="my-5">
              <form action="/action_page.php">
                <div className="mb-3">
                  <input type="text" name="name" className="form-control mx-auto" placeholder="Name" style={{ border: "1px solid #ddd", padding: "10px", borderRadius: "5px" }} />
                </div>
                <div className="mb-3">
                  <input type="email" name="email" className="form-control mx-auto" placeholder="Email" style={{ border: "1px solid #ddd", padding: "10px", borderRadius: "5px" }} />
                </div>
                <div className="mb-3">
                  <input type="text" name="msg" className="form-control mx-auto" placeholder="Message" style={{ border: "1px solid #ddd", padding: "10px", borderRadius: "5px" }} />
                </div>
                <button type="submit" className="btn text-light mb-5" style={{ marginTop: "30px", backgroundColor: "#2eac6d", height: "50px", width: "200px", borderRadius: "30px", boxShadow: "0px 4px 6px rgba(0, 0, 0, 0.2)", transition: "all 0.3s" }}>
                  GET IN TOUCH
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

    {/* <--- SECTION-8 --->*/}
    <div id="blogs" className="container-fluid my-5">
      <div className="row text-center">
        <h4 className="mx-auto my-5" style={{ fontSize: "20px", fontFamily: "Lucida Sans, sans-serif", letterSpacing: "5px" }}>
          OUR BLOGS
        </h4>
        <h1 className="mx-auto my-4" style={{ fontFamily: "Oswald, sans-serif" }}>
          LATEST ARTICLES
        </h1>
      </div>

      <div className="row g-4 justify-content-center">
        {articles.map((article, index) => (
          <div key={index} className="col-md-4">
            <div className="card h-100" style={{ border: "none" }}>
              <img src={article.urlToImage || "default-image.jpg"} alt="Blog Image" height="254px" />
              <div className="text-center">
                <p className="my-5 text-dark" style={{ fontFamily: "Oswald, sans-serif", fontSize: "25px", fontWeight: "500" }}>
                  {article.title}
                </p>
                <p className="my-5 text-dark">{new Date(article.publishedAt).toDateString()} • <a href={article.url} target="_blank" rel="noopener noreferrer" style={{ marginLeft: "5px", color: "black" }}> Read more </a></p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>

    {/* <--- FOOTER ---> */}
    <footer className="bg-dark text-light text-center py-3">
        <div>© Raj Patel · <a href="#" className="text-decoration-none text-light">Privacy</a> · <a href="#" className="text-decoration-none text-light">Terms</a></div>
        <a href="#" className="text-decoration-none text-light">Back to top</a>
    </footer>
    </>
  );
};

export default Home;
