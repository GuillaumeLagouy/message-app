import './App.css';
import MessageForm from './components/MessageForm';
import MessageList from './components/MessageList';
import { ThemeProvider } from './components/theme-provider';
import { useMessages } from './hooks/useMessages';

function App() {
  const { messages, sendMessage } = useMessages();

  return (
    <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
      <main className="container mx-auto flex h-dvh flex-col overflow-hidden lg:flex-row">
        <section className="lg:w-1/2 lg:p-3">
          <MessageForm onSendMessage={sendMessage} />
        </section>
        <section className="flex flex-col lg:w-1/2 lg:p-3">
          <MessageList messages={messages} />
        </section>
      </main>
    </ThemeProvider>
  );
}

export default App;
