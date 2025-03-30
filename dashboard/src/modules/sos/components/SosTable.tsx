import { getStatus } from "@/modules/sos/utils/getStatus";
import {
  Accordion,
  AccordionDetails,
  AccordionSummary,
  Button,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableRow,
  Typography,
} from "@mui/material";
import { SOSInterface } from "@/modules/sos/interfaces";
import { SosStatusEnum } from "@/modules/sos/interfaces";
import { format, parseISO } from "date-fns";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import { formatMessage } from "@/modules/chats/utils/formatMessage";
import { getColor } from "@/modules/sos/utils/getColor";
import { useState } from "react";
import SosModal from "@/modules/sos/components/SosModal";

const SosTable = ({ data }: { data: SOSInterface[] }) => {
  const date = data.length > 0 ? parseISO(data[0].createDate) : null;

  const [showModal, setShowModal] = useState(false);
  const [cellData, setCellDAta] = useState<SOSInterface | null>(null);

  const onButtonClick = (data: SOSInterface) => {
    setCellDAta(data);
    setShowModal(true);
  };

  const onCloseModal = () => {
    setShowModal(false);
    setCellDAta(null);
  };

  return (
    <>
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
            {data.map((row) => {
              return (
                <TableRow
                  sx={{ background: getColor(row.status).background }}
                  key={row.id}
                >
                  <TableCell
                    style={{ color: getColor(row.status).color }}
                    align="left"
                    sx={{ maxWidth: "300px" }}
                  >
                    {row.address}
                  </TableCell>
                  <TableCell
                    style={{ color: getColor(row.status).color }}
                    align="center"
                    sx={{ display: "flex", justifyContent: "center" }}
                  >
                    <Accordion sx={{ maxWidth: "300px" }}>
                      <AccordionSummary
                        expandIcon={<ExpandMoreIcon />}
                        aria-controls="panel1-content"
                        id="panel1-header"
                      >
                        <Typography component="span">
                          {formatMessage(row.comment, 30)}
                        </Typography>
                      </AccordionSummary>
                      <AccordionDetails>{row.comment}</AccordionDetails>
                    </Accordion>
                  </TableCell>
                  <TableCell
                    style={{ color: getColor(row.status).color }}
                    align="center"
                  >
                    {row.phoneNumber}
                  </TableCell>
                  <TableCell
                    style={{ color: getColor(row.status).color }}
                    align="center"
                  >
                    {[
                      date ? format(date, "dd.MM.yyyy") : "",
                      date ? format(date, "HH:mm:ss") : "",
                    ].join(" ")}
                  </TableCell>
                  <TableCell
                    style={{ color: getColor(row.status).color }}
                    align="center"
                  >
                    {getStatus(row.status)}
                  </TableCell>
                  <TableCell align="center">
                    {row.status === SosStatusEnum.new && (
                      <Button
                        onClick={() => onButtonClick(row)}
                        variant="contained"
                      >
                        Подробнее
                      </Button>
                    )}
                  </TableCell>
                </TableRow>
              );
            })}
          </TableBody>
        </Table>
      </TableContainer>
      <SosModal onClose={onCloseModal} open={showModal} data={cellData} />
    </>
  );
};

export default SosTable;
