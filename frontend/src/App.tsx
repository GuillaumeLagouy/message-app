import { useState } from 'react';
import './App.css';
import MessageForm from './components/MessageForm';
import MessageList from './components/MessageList';

function App() {
  const [hasSent, setHasSent] = useState<number>(0);

  const handleNewMessage = () => {
    // Passer l'état à true puis à false (le processus ne doit pas être bloquant)
    // TODO : À revoir
    setHasSent((prevCount) => prevCount + 1);
  };

  return (
    <div>
      <MessageForm onMessageSent={handleNewMessage} />
      <MessageList hasSent={hasSent} />
    </div>
  );
}

export default App;
