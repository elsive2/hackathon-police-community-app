import {
  Button,
  Card,
  CardActions,
  CardContent,
  Link,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableRow,
  Typography,
} from "@mui/material";
import { ApplicationInterface } from "@/modules/applications/interfaces";
import { CategoryInterface } from "@/interfaces/category";
import { format, parseISO } from "date-fns";
import { useState } from "react";

const ChatsTable = ({
  data,
  categories,
}: {
  data: ApplicationInterface[];
  categories: CategoryInterface[];
}) => {
  const getCategoryName = (name: string) => {
    const category = categories.find((category) => category.name === name);

    if (!category) return null;

    return category.displayName;
  };

  console.log(data)

  return (
    <div>
      
          {data.map((row) => {
            const [expanded, setExpanded] = useState(false);
            const date = parseISO(row.createDate);

            return (
            <Card sx={{ maxWidth: "100%" }} key={row.id}>
              <CardContent>
                <Typography
                  sx={{
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "space-between",
                  }}
                  gutterBottom
                  component="div"
                >
                  <Typography gutterBottom variant="h6" component="div">
                    {row.title}
                  </Typography>
                  <Typography
                    gutterBottom
                    variant="body2"
                    sx={{ color: "text.secondary" }}
                    component="span"
                  >
                    Дата поступления: {format(date, "dd.MM.yyyy")}{" "}
                    {format(date, "HH:mm:ss")}
                  </Typography>
                </Typography>

                <Typography
                  
                  component="div"
                />
                <Typography>Категория: {getCategoryName(row.category)}</Typography>
                <Typography>Статус: {row.status === "NEW" ? "Новый" : row.status}</Typography>
                <Typography gutterBottom >Местоположение: {row.address}</Typography>
                <Typography>{expanded ? row.content : ""}</Typography>
              </CardContent>
              <CardActions>
                
                <Button
                  size="small"
                  variant="contained"
                  onClick={() => setExpanded(!expanded)}
                >
                  {expanded ? "Скрыть" : "Подробнее"}
                </Button>
              </CardActions>
            </Card>
              // <>
              //   <TableRow key={row.id}>
              //     <TableCell>{getCategoryName(row.category)}</TableCell>
              //     <TableCell>{row.title}</TableCell>
              //     <TableCell>{row.content}</TableCell>
              //     <TableCell>{format(date, "dd.MM.yyyy")}{" "}
              //                 {format(date, "HH:mm:ss")}</TableCell>
              //     <TableCell>{row.status}</TableCell>
              //   </TableRow>
              // </>
            );
          })}
        
    </div>
  );
};

export default ChatsTable;
