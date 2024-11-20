import { useForm } from 'react-hook-form';
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from './ui/card';
import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from './ui/form';
import { Textarea } from './ui/textarea';
import { Button } from './ui/button';
import { useEffect } from 'react';
import { Message } from '@/types/MessageType';

const formSchema = z.object({
  content: z
    .string()
    .min(1, { message: 'The content field is required' })
    .regex(/^[^<>]*$/, { message: 'HTML tags are not allowed' }),
});

interface MessageFormProps {
  onSendMessage: (message: string) => Promise<boolean>;
  onEditMessage: (id: number, message: string) => Promise<boolean>;
  initialMessage?: Message;
  resetInitialMessage: () => void;
}

export default function MessageForm({
  onSendMessage,
  onEditMessage,
  initialMessage,
  resetInitialMessage,
}: MessageFormProps) {
  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      content: '',
    },
  });

  async function onSubmit(data: z.infer<typeof formSchema>) {
    let success = false;

    if (initialMessage) {
      success = await onEditMessage(initialMessage.id, data.content);
      resetInitialMessage();
    } else {
      success = await onSendMessage(data.content);
    }

    if (success) {
      form.reset();
    }
  }

  useEffect(() => {
    if (initialMessage) {
      form.setValue('content', initialMessage.message);
    }
  }, [initialMessage, form.setValue, form]);

  return (
    <Card>
      <CardHeader>
        <CardTitle className="text-lg">Send a message</CardTitle>
        <CardDescription>Write a message and send it !</CardDescription>
      </CardHeader>
      <CardContent>
        <Form {...form}>
          <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
            <FormField
              control={form.control}
              name="content"
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Content</FormLabel>
                  <FormControl>
                    <Textarea
                      placeholder="Tell us a little bit about yourself"
                      className="resize-none"
                      {...field}
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <Button type="submit">
              {initialMessage?.message ? 'Modify' : 'Send'}
            </Button>
          </form>
        </Form>
      </CardContent>
    </Card>
  );
}
