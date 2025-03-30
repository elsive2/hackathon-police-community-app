import React, { useState } from 'react'
import { Button, Card, CardActions, CardContent, CardMedia, Typography } from "@mui/material";
import { format, parseISO } from "date-fns";
import { formatMessage } from '@/modules/chats/utils/formatMessage';
import Link from 'next/link';

interface NewsCardProps {
    id: number;
    title: string;
    content: string;
    creationDate: string;
}

const NewsCard: React.FC<NewsCardProps> = ({ id, title, content, creationDate }) => {

  const [expanded, setExpanded] = useState(false);

    const date = parseISO(creationDate);
    
  return (
    <Card sx={{ maxWidth: "100%"}} key={id}>
          <CardContent>
            <Typography sx={{display: "flex", alignItems: "center", justifyContent: "space-between"}} gutterBottom>
                <Typography gutterBottom variant='h6' component="div">
                    {title}
                </Typography>
                <Typography gutterBottom variant="body2" sx={{ color: 'text.secondary' }}>
                    Дата публикации: {format(date, "dd.MM.yyyy")} {format(date, "HH:mm:ss")}
                </Typography>
            </Typography>

            <Typography dangerouslySetInnerHTML={{ __html: expanded ? content : formatMessage(content, 500) }} component="div" />

          </CardContent>
          <CardActions>
          <Link href={"/dashboard/news/edit/" + id}>
            <Button size="small" variant="outlined">Редактировать</Button>
          </Link>
          <Button size="small" variant="contained" onClick={() => setExpanded(!expanded)}>
                    {expanded ? "Скрыть" : "Подробнее"}
                </Button>
          </CardActions>
        </Card>


  )
}

export default NewsCard