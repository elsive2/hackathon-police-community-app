"use client";

import { SOSInterface } from "@/modules/sos/interfaces";
import { useEffect, useState } from "react";
import { sosData } from "@/modules/sos/mock";
import { getStatus } from "@/modules/sos/utils/get-status";
import { Button, Checkbox, Paper, Table, TableBody, TableCell, TableContainer, TableRow } from "@mui/material";

const SosPage = () => {
  const [data, setData] = useState<SOSInterface[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setLoading(true);
    setTimeout(() => {
      setData(sosData);
      setLoading(false);
    }, 1500);
  }, []);

  if (loading) return <div>Loading...</div>;

  return (
    <TableContainer component={Paper}>
      <Table>
        <TableBody>
        <TableRow>
            <TableCell align="left">Местоположение</TableCell>
            <TableCell align="center">Телефон</TableCell>
            <TableCell align="center">Дата</TableCell>
            <TableCell align="center">Статус</TableCell>
            <TableCell align="center">Кнопки</TableCell>
          </TableRow>
          {data.map((row, index) => {
            return (
              <TableRow
                key={row.id}
                sx={{ cursor: "pointer" }}
              >
                <TableCell align="left">{row.location}</TableCell>
                <TableCell align="center">{row.phone}</TableCell>
                <TableCell align="center">{row.date}</TableCell>
                <TableCell align="center">{getStatus(row.status)}</TableCell>
                <TableCell align="center" ><Button variant="outlined">Принять</Button></TableCell>
              </TableRow>
            );
          })}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default SosPage;
