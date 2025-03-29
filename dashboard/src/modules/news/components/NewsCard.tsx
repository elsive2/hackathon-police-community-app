import React from 'react'
import { Button, Card, CardActions, CardContent, CardMedia, Typography } from "@mui/material";
import { format, parseISO } from "date-fns";
import { formatMessage } from '@/modules/chats/utils/formatMessage';

interface NewsCardProps {
    id: number;
    title: string;
    content: string;
    creationDate: string;
}

const NewsCard: React.FC<NewsCardProps> = ({ id, title, content, creationDate }) => {

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

            <Typography dangerouslySetInnerHTML={{__html: formatMessage(content, 500)}} component="div" />

          </CardContent>
          <CardActions>
            <Button size="small" variant="outlined">Редактировать</Button>
            <Button size="small" variant="contained">Подробнее</Button>
          </CardActions>
        </Card>
  )
}

export default NewsCard