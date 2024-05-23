import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'

//Setup redux
import store from './redux/configStore.jsx';
import { Provider } from 'react-redux';

ReactDOM.createRoot(document.getElementById('root')).render(
  <Provider store={store}>
    <App />
  </Provider>
  ,
)
