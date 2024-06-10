// ReusableDataGrid.jsx
import React from 'react';
import PropTypes from 'prop-types';
import { DataGrid } from '@mui/x-data-grid';

const Table = ({ columns, rows, pageSize, getRowId,...props }) => {
    return (
        <div style={{ height: '70vh', width: '100%' }}>
            <DataGrid
                columns={columns}
                rows={rows}
                initialState={{
                    pagination: {
                        paginationModel: { page: 0, pageSize: pageSize },
                    },
                }}
                getRowId={getRowId}
                {...props}
            />
        </div>
    );
};

Table.propTypes = {
    columns: PropTypes.arrayOf(
        PropTypes.shape({
            field: PropTypes.string.isRequired,
            headerName: PropTypes.string.isRequired,
            width: PropTypes.number
        })
    ).isRequired,
    rows: PropTypes.arrayOf(
        PropTypes.shape({
            id: PropTypes.number.isRequired
        })
    ).isRequired,
    pageSize: PropTypes.number,
    pageSizeOptions: PropTypes.arrayOf(PropTypes.number),
};

Table.defaultProps = {
    pageSize: 5,
    pageSizeOptions: [5, 10],
    checkboxSelection: false,
};

export default Table;