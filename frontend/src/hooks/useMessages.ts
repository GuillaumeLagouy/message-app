import { useCallback, useEffect, useState } from 'react';
import { Message } from '../types/MessageType';
import { getMessages, postMessage } from '../services/apiService';

export function useMessages() {
  const [messages, setMessages] = useState<Message[]>([]);

  const loadMessages = useCallback(async () => {
    try {
      const data = await getMessages();
      console.log('Message loaded');
      setMessages(data);
    } catch (error) {
      console.log(error);
    }
  }, []);

  useEffect(() => {
    loadMessages();
  }, [loadMessages]);

  const sendMessage = async (content: string) => {
    try {
      await postMessage({ content: content });
      await loadMessages();
      return true;
    } catch (error) {
      console.log(error);
      return false;
    }
  };

  return { messages, sendMessage };
}
