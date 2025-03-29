import { Button, Paper, Skeleton, Table, TableBody, TableCell, TableContainer, TableRow } from '@mui/material';
import React from 'react'

const SkeletonTable = () => {

    const data = Array.from({ length: 20 }, () => ({}));

  return (
    <TableContainer component={Paper}>
      <Table>
        <TableBody>
        <TableRow>
            <TableCell align="left" sx={{ width: "31%" }}>Местоположение</TableCell>
            <TableCell align="center">Телефон</TableCell>
            <TableCell align="center">Дата</TableCell>
            <TableCell align="center">Статус</TableCell>
            <TableCell align="center"></TableCell>
          </TableRow>
          {data.map((row, index) => {
            return (
              <TableRow
                key={index}
                sx={{ cursor: "pointer" }}
              >
                <TableCell align="left" sx={{ width: "31%" }}><Skeleton animation="wave" /></TableCell>
                <TableCell align="center"><Skeleton animation="wave" /></TableCell>
                <TableCell align="center"><Skeleton animation="wave" /></TableCell>
                <TableCell align="center"><Skeleton animation="wave" /></TableCell>
                <TableCell align="center" ><Skeleton animation="wave" /></TableCell>
              </TableRow>
            );
          })}
        </TableBody>
      </Table>
    </TableContainer>
  )
}

export default SkeletonTable