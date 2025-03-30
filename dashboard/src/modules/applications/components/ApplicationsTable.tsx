import {
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableRow,
} from "@mui/material";
import { ApplicationInterface } from "@/modules/applications/interfaces";
import { CategoryInterface } from "@/interfaces/category";
import { format, parseISO } from "date-fns";

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

  return (
    <TableContainer component={Paper}>
      <Table>
        <TableBody>
          <TableRow>
            <TableCell align="left">Категория</TableCell>
            <TableCell align="left">Заголовок</TableCell>
            <TableCell align="left">Детали</TableCell>
            <TableCell align="left">Дата</TableCell>
            <TableCell align="left">Статус</TableCell>
          </TableRow>
          {data.map((row) => {
            const date = parseISO(row.createDate);
            return (
              <>
                <TableRow key={row.id}>
                  <TableCell>{getCategoryName(row.category)}</TableCell>
                  <TableCell>{row.title}</TableCell>
                  <TableCell>{row.content}</TableCell>
                  <TableCell>{format(date, "dd.MM.yyyy")}</TableCell>
                  <TableCell>{row.status}</TableCell>
                </TableRow>
              </>
            );
          })}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default ChatsTable;
