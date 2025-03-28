"use client";

import { SOSInterface } from "@/modules/sos/interfaces";
import { useEffect, useState } from "react";
import { sosData } from "@/modules/sos/mock";
import { getStatus } from "@/modules/sos/utils/get-status";
import { Checkbox, Paper, Table, TableBody, TableCell, TableContainer, TableRow } from "@mui/material";

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
          {data.map((row, index) => {
            const labelId = index;

            return (
              <TableRow
                hover
                role="checkbox"
                key={row.id}
                sx={{ cursor: "pointer" }}
              >
                <TableCell
                  component="th"
                  scope="row"
                  padding="none"
                >
                  {row.location}
                </TableCell>
                <TableCell align="right">{row.phone}</TableCell>
                <TableCell align="right">{row.date}</TableCell>
                <TableCell align="right">{row.status}</TableCell>
              </TableRow>
            );
          })}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default SosPage;
