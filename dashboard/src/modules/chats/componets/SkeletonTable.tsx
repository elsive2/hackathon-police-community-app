import React from 'react'
import { Button, Paper, Skeleton, Table, TableBody, TableCell, TableContainer, TableRow } from "@mui/material";

export const SkeletonTable = () => {

    const data = Array.from({ length: 20 }, () => ({}));

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
                key={index}
                sx={{ cursor: "pointer" }}
              >
                <TableCell align="left" sx={{width: "19%"}}><Skeleton animation="wave" /></TableCell>
                <TableCell 
                align="center" 
                sx={{whiteSpace: "nowrap", overflow: "hidden", textOverflow: "ellipsis", maxWidth: "200px", textAlign: "start"}}>
                    <Skeleton animation="wave" />
                    </TableCell>
                <TableCell align="center"><Skeleton animation="wave" /></TableCell>
                <TableCell align="center"><Skeleton animation="wave" /></TableCell>
              </TableRow>
            );
          })}
        </TableBody>
      </Table>
    </TableContainer>
  )
}
