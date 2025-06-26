import Layout from './modules/Layout'
import Home from './modules/Home'
import About from './modules/About'
import Public from './modules/Public'
import NoPage from './modules/NoPage'
import Login from './modules/Login'
import { Routes } from 'react-router-dom'
import { Route } from 'react-router-dom'
import './App.css'

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<Home />} />
        <Route path="public" element={<Public />} />
        <Route path="about" element={<About />} />
        <Route path="login" element={<Login />} />
        <Route path="*" element={<NoPage />} />
      </Route>
    </Routes>
  )

}

export default App
