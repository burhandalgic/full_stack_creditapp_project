import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { store } from './redux/store';  // Redux store'u içeri aktarıyoruz
import { Provider } from 'react-redux';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>

  <Provider store={store}>  {/* Redux Provider ile uygulamayı sarıyoruz */}
    <App />
  </Provider>
    
  </React.StrictMode>

);

reportWebVitals();
