import { useState } from 'react';
import './App.css';
import MessageForm from './components/MessageForm';
import MessageList from './components/MessageList';
import { ThemeProvider } from './components/theme-provider';
import { useMessages } from './hooks/useMessages';
import { Message } from './types/MessageType';

function App() {
  const { messages, sendMessage, editMessage } = useMessages();
  const [newMessage, setNewMessage] = useState<Message>();

  const handleEdit = (message: Message) => {
    setNewMessage(message);
  };

  const resetMessage = () => {
    setNewMessage(undefined);
  };

  return (
    <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
      <main className="container mx-auto flex h-dvh flex-col lg:flex-row">
        <section className="p-3 lg:w-1/2">
          <MessageForm
            onSendMessage={sendMessage}
            onEditMessage={editMessage}
            initialMessage={newMessage}
            resetInitialMessage={resetMessage}
          />
        </section>
        <section className="flex flex-col overflow-hidden p-3 lg:w-1/2">
          <MessageList messages={messages} onEdit={handleEdit} />
        </section>
      </main>
    </ThemeProvider>
  );
}

export default App;
