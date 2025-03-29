import { getStatus } from "@/modules/sos/utils/getStatus";
import { Button, Paper, Table, TableBody, TableCell, TableContainer, TableRow } from "@mui/material";
import { SOSInterface } from "@/modules/sos/interfaces";
import { SosStatusEnum } from "@/modules/sos/interfaces";

const SosTable = ({ data }: {data: SOSInterface[]}) => {
    return (
        <TableContainer component={Paper}>
      <Table>
        <TableBody>
        <TableRow>
            <TableCell align="left">Местоположение</TableCell>
            <TableCell align="center">Телефон</TableCell>
            <TableCell align="center">Дата</TableCell>
            <TableCell align="center">Статус</TableCell>
            <TableCell align="center"></TableCell>
          </TableRow>
          {data.map((row, index) => {
            return (
              <TableRow
                key={row.id}
                sx={{ cursor: "pointer" }}
              >
                <TableCell align="left" >{row.location}</TableCell>
                <TableCell align="center">{row.phone}</TableCell>
                <TableCell align="center">{row.date}</TableCell>
                <TableCell align="center">{getStatus(row.status)}</TableCell>
                <TableCell align="center" >{row.status === SosStatusEnum.new && <Button variant="outlined">Принять</Button>}</TableCell>
              </TableRow>
            );
          })}
        </TableBody>
      </Table>
    </TableContainer>
    )
}

export default SosTable