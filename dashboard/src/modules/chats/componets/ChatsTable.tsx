import { Button, Paper, Table, TableBody, TableCell, TableContainer, TableRow } from "@mui/material";
import { ChatInterface } from "../interfaces";
import { formatMessage } from "../utils/formatMessage";

const ChatsTable = ({ data }: {data: ChatInterface[]}) => {

    return (
        <TableContainer component={Paper}>
      <Table>
        <TableBody>
        <TableRow>
            <TableCell align="left">Телефон</TableCell>
            <TableCell align="center">Сообщение</TableCell>
            <TableCell align="center">Дата</TableCell>
            <TableCell align="center"></TableCell>
          </TableRow>
          {data.map((row, index) => {
            return (
              <TableRow
                key={row.id}
                sx={{ cursor: "pointer" }}
              >
                <TableCell align="left" >{row.phone}</TableCell>
                <TableCell 
                align="center" 
                sx={{whiteSpace: "nowrap", overflow: "hidden", textOverflow: "ellipsis", maxWidth: "200px", textAlign: "start"}}>
                    {formatMessage(row.startMessage, 40)}
                    </TableCell>
                <TableCell align="center">{row.date}</TableCell>
                <TableCell align="center">{row.isNew && <Button variant="outlined">Принять</Button>}</TableCell>
              </TableRow>
            );
          })}
        </TableBody>
      </Table>
    </TableContainer>
    )
}

export default ChatsTable