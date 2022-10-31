import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import { UserSingupPage } from './pages/UserSingupPage'

function App() {
  const [count, setCount] = useState(0)

  return (
    <UserSingupPage></UserSingupPage>
    
  )
}

export default App
