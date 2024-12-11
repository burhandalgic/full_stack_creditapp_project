import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Credit from './components/Credit';
import Customer from './components/Customer';
import './App.css'; 

const App = () => {
  return (
    <Router>
      <div>
        {/* Navbar veya Sayfa Geçiş Linkleri */}
        <nav>
          <ul>
          
            <li>
            <Link to="/customer" className="button-link">Ana Sayfa</Link>
            </li>
          </ul>
        </nav>

        {/* Routes */}
        <Routes>
          <Route path="/credit" element={<Credit />} />
          <Route path="/customer" element={<Customer />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;


