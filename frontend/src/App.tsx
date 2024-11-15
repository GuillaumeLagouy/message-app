import './App.css';
import MessageForm from './components/MessageForm';
import MessageList from './components/MessageList';
import { useMessages } from './hooks/useMessages';

function App() {
  const { messages, sendMessage } = useMessages();

  return (
    <div>
      <MessageForm onSendMessage={sendMessage} />
      <MessageList messages={messages} />
    </div>
  );
}

export default App;
