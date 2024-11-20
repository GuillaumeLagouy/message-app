import { PencilIcon } from 'lucide-react';
import { Message } from '../types/MessageType';
import { Button } from './ui/button';
import {
  Card,
  CardContent,
  CardFooter,
  CardHeader,
  CardTitle,
} from './ui/card';
import { formatDate } from '@/utils';

interface MessageListProps {
  messages: Message[];
  onEdit: (newMessage: Message) => void;
}

export default function MessageList({ messages, onEdit }: MessageListProps) {
  return (
    <>
      <h1 className="scroll-m-20 border-b pb-2 text-3xl font-semibold tracking-tight first:mt-0">
        Message feed
      </h1>
      <section className="mt-2 flex h-full flex-col gap-2 overflow-y-scroll">
        {messages.map((message) => (
          <Card key={message.id}>
            <CardHeader>
              <CardTitle>{formatDate(message.postedAt.toString())}</CardTitle>
            </CardHeader>
            <CardContent>
              <p>{message.message}</p>
            </CardContent>
            <CardFooter>
              <Button variant={'outline'} onClick={() => onEdit(message)}>
                <PencilIcon /> Edit
              </Button>
            </CardFooter>
          </Card>
        ))}
      </section>
    </>
  );
}
