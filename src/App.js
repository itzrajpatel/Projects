import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Contact from "./pages/Contact";
import Shop from "./pages/Shop";
import Login from "./pages/Login";
import Order from "./pages/Order";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/contact" element={<Contact />} />
        <Route path="/shop" element={<Shop />} />
        <Route path="/login" element={<Login />} />
        <Route path="/orders" element={<Order />} />
      </Routes>
    </Router>
  );
}

export default App;
