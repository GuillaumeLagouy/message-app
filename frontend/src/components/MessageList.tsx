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

interface MessageListProps {
  messages: Message[];
}

export default function MessageList({ messages }: MessageListProps) {
  return (
    <>
      <h1 className="scroll-m-20 border-b pb-2 text-3xl font-semibold tracking-tight first:mt-0">
        Liste des messages
      </h1>
      <section className="mt-2 flex h-full flex-col gap-2 overflow-scroll">
        {messages.map((message) => (
          <Card key={message.id}>
            <CardHeader>
              <CardTitle>{message.postedAt.toString()}</CardTitle>
            </CardHeader>
            <CardContent>
              <p>{message.message}</p>
            </CardContent>
            <CardFooter>
              <Button variant={'outline'}>
                <PencilIcon /> Edit
              </Button>
            </CardFooter>
          </Card>
        ))}
      </section>
    </>
  );
}
