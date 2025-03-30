import { getStatus } from "@/modules/sos/utils/getStatus";
import { Accordion, AccordionDetails, AccordionSummary, Button, Paper, Table, TableBody, TableCell, TableContainer, TableRow, Typography } from "@mui/material";
import { SOSInterface } from "@/modules/sos/interfaces";
import { SosStatusEnum } from "@/modules/sos/interfaces";
import { format, parseISO } from "date-fns";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { formatMessage } from "@/modules/chats/utils/formatMessage";

const SosTable = ({ data }: {data: SOSInterface[]}) => {
    const date = data.length > 0 ? parseISO(data[0].createDate) : null;
    

    return (
        <TableContainer component={Paper}>
      <Table>
        <TableBody>
        <TableRow>
            <TableCell align="left">Местоположение</TableCell>
            <TableCell align="center">Комментарий</TableCell>
            <TableCell align="center">Телефон</TableCell>
            <TableCell align="center">Дата</TableCell>
            <TableCell align="center">Статус</TableCell>
            <TableCell align="center"></TableCell>
          </TableRow>
          {data.map((row, index) => {
            return (
              <TableRow
                key={row.id}
              >
                <TableCell align="left" sx={{maxWidth: "300px"}}>{row.address}</TableCell>
                <TableCell align="center" sx={{display: "flex", justifyContent: "center"}}>
                <Accordion sx={{ maxWidth: "300px"}} >
                  <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1-content"
                    id="panel1-header"
                    
                  >
                    <Typography component="span">{formatMessage(row.comment, 30)}</Typography>
                  </AccordionSummary>
                  <AccordionDetails>
                  {row.comment}
                  </AccordionDetails>
                </Accordion>
                </TableCell>
                <TableCell align="center">{row.phoneNumber}</TableCell>
                <TableCell align="center">{[date ? format(date, "dd.MM.yyyy") : "", date ? format(date, "HH:mm:ss") : ""].join(" ")}</TableCell>
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